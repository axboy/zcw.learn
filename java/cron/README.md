# Cron Task

### Timer

Timer是一中定时器，用一个后台线程执行指定任务一次或多次。
Timer主要涉及两个类，TimerTask, Timer.

- TimerTask.java

抽象任务，自定义任务类继承TimerTask类，重载run方法。

```java
public abstract class TimerTask implements Runnable {
    
    //用于控制任务，同步锁
    final Object lock = new Object();

    //任务状态
    int state = VIRGIN;

    //初始状态，未执行
    static final int VIRGIN = 0;

    //已执行
    static final int SCHEDULED   = 1;

    //非周期任务已执行、或正在执行
    static final int EXECUTED    = 2;

    //任务已取消
    static final int CANCELLED   = 3;

    //任务下一次执行时间
    long nextExecutionTime;

    //正值表示固定速率执行，负值表示固定延时执行，0表示非周期任务。
    long period = 0;

    //默认构造函数
    protected TimerTask() {
    }

    //待重载方法
    public abstract void run();

    //取消任务
    public boolean cancel() {
        synchronized(lock) {
            boolean result = (state == SCHEDULED);
            state = CANCELLED;
            return result;
        }
    }

    //获取下一次执行任务的时间
    public long scheduledExecutionTime() {
        synchronized(lock) {
            return (period < 0 ? nextExecutionTime + period : nextExecutionTime - period);
        }
    }
}
```

- Timer.java



```java
public class Timer {
    // 任务队列
    private final TaskQueue queue = new TaskQueue();
    // timer任务处理线程，轮寻
    private final TimerThread thread = new TimerThread(queue);
    
    private final Object threadReaper = new Object() {
        protected void finalize() throws Throwable {
            synchronized(queue) {
                thread.newTasksMayBeScheduled = false;
                queue.notify(); // In case queue is empty.
            }
        }
    };
    
    // ...  删减构造函数
    
    //传入一个任务，delay毫秒后开始执行，只执行一遍
    public void schedule(TimerTask task, long delay) {
        if (delay < 0)
            throw new IllegalArgumentException("Negative delay.");
        sched(task, System.currentTimeMillis()+delay, 0);
    }
    //传入一个任务，指定时间执行，如果时间已过，则立即执行，只执行一遍
    public void schedule(TimerTask task, Date time) {
        sched(task, time.getTime(), 0);
    }
    //传入一个任务，delay毫秒后开始执行，循环周期时间为period毫秒
    public void schedule(TimerTask task, long delay, long period) {
        if (delay < 0)
            throw new IllegalArgumentException("Negative delay.");
        if (period <= 0)
            throw new IllegalArgumentException("Non-positive period.");
        sched(task, System.currentTimeMillis()+delay, -period);
    }
    //传入一个任务，指定第一次执行时间firstTime和循环周期period
    public void schedule(TimerTask task, Date firstTime, long period) {
        if (period <= 0)
            throw new IllegalArgumentException("Non-positive period.");
        sched(task, firstTime.getTime(), -period);
    }
    
    
    public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
        if (delay < 0)
            throw new IllegalArgumentException("Negative delay.");
        if (period <= 0)
            throw new IllegalArgumentException("Non-positive period.");
        sched(task, System.currentTimeMillis()+delay, period);
    }
    public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period) {
        if (period <= 0)
            throw new IllegalArgumentException("Non-positive period.");
        sched(task, firstTime.getTime(), period);
    }
    
    //以上方法最终调用的方法，把任务添加到任务队列
    private void sched(TimerTask task, long time, long period) {
        if (time < 0)
            throw new IllegalArgumentException("Illegal execution time.");

        // Constrain value of period sufficiently to prevent numeric
        // overflow while still being effectively infinitely large.
        if (Math.abs(period) > (Long.MAX_VALUE >> 1))
            period >>= 1;

        synchronized(queue) {
            if (!thread.newTasksMayBeScheduled)
                throw new IllegalStateException("Timer already cancelled.");

            synchronized(task.lock) {
                if (task.state != TimerTask.VIRGIN)
                    throw new IllegalStateException(
                        "Task already scheduled or cancelled");
                task.nextExecutionTime = time;
                task.period = period;
                task.state = TimerTask.SCHEDULED;
            }

            queue.add(task);
            if (queue.getMin() == task)
                queue.notify();
        }
    }
    
    //取消所有任务
    public void cancel() {
        synchronized(queue) {
            thread.newTasksMayBeScheduled = false;
            queue.clear();
            queue.notify();  // In case queue was already empty.
        }
    }
    
    //移除所有已取消的任务，返回移除的任务数
     public int purge() {
         int result = 0;

         synchronized(queue) {
             for (int i = queue.size(); i > 0; i--) {
                 if (queue.get(i).state == TimerTask.CANCELLED) {
                     queue.quickRemove(i);
                     result++;
                 }
             }

             if (result != 0)
                 queue.heapify();
         }

         return result;
     }
}
```

```java
class TimerThread extends Thread {
    //
    boolean newTasksMayBeScheduled = true;

    //任务队列
    private TaskQueue queue;

    TimerThread(TaskQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            mainLoop();
        } finally {
            // Someone killed this Thread, behave as if Timer cancelled
            synchronized(queue) {
                newTasksMayBeScheduled = false;
                queue.clear();  // Eliminate obsolete references
            }
        }
    }

    //主循环
    private void mainLoop() {
        while (true) {
            try {
                TimerTask task;
                boolean taskFired;
                synchronized(queue) {
                    // Wait for queue to become non-empty
                    while (queue.isEmpty() && newTasksMayBeScheduled)
                        queue.wait();
                    if (queue.isEmpty())
                        break; // Queue is empty and will forever remain; die

                    // Queue nonempty; look at first evt and do the right thing
                    long currentTime, executionTime;
                    task = queue.getMin();
                    synchronized(task.lock) {
                        if (task.state == TimerTask.CANCELLED) {
                            queue.removeMin();
                            continue;  // No action required, poll queue again
                        }
                        currentTime = System.currentTimeMillis();
                        executionTime = task.nextExecutionTime;
                        if (taskFired = (executionTime<=currentTime)) {
                            if (task.period == 0) { // Non-repeating, remove
                                queue.removeMin();
                                task.state = TimerTask.EXECUTED;
                            } else { // Repeating task, reschedule
                                queue.rescheduleMin(
                                  task.period<0 ? currentTime   - task.period
                                                : executionTime + task.period);
                            }
                        }
                    }
                    if (!taskFired) // Task hasn't yet fired; wait
                        queue.wait(executionTime - currentTime);
                }
                if (taskFired)  // Task fired; run it, holding no locks
                    task.run();
            } catch(InterruptedException e) {
            }
        }
    }
}
```

### Quartz

TODO