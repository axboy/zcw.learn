package local.zcw.demo.cron.timer;

import java.util.Date;
import java.util.TimerTask;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/4/24 14:53
 * 一个耗时久的任务
 */
public class MyLongTimerTask extends TimerTask {

    private String taskName;


    public MyLongTimerTask(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.printf("%s, %s\n", this.taskName, new Date());
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
