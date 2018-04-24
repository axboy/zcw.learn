package local.zcw.demo.cron.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/4/16 16:09
 * Main Function
 */
public class Main {
    public static void main(String[] args) {
        System.out.printf("Now time: %s\n", new Date());
        Timer timer = new Timer();

        //时间减10s
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, -10);

        //定义两个任务
        MySimpleTimerTask simpleTask = new MySimpleTimerTask("Simple Task");
        MyLongTimerTask longTask = new MyLongTimerTask("Long Task");

        //测试schedule(...)方法

        //测试简单任务，时间未过
        //结果: 2s后执行第一次，之后间隔3s执行
        //timer.schedule(simpleTask, 2000L, 3000L);

        //测试复杂任务，时间未过
        //结果: 2s后执行第一次，之后间隔5s执行
        //timer.schedule(longTask, 2000L, 3000L);

        //测试简单任务，当前时间超过设定的时间
        //结果: 立即执行第一次，之后间隔3s执行
        //timer.schedule(simpleTask, cal.getTime(), 3000L);

        //测试复杂任务，当前时间超过设定的时间
        //结果: 立即执行第一次，之后间隔5s执行
        //timer.schedule(longTask,cal.getTime(),3000L);

        //////测试scheduleAtFixedRate(...)方法

        //简单任务，时间未过
        //结果: 2s后执行第一次，之后间隔3s执行
        //timer.scheduleAtFixedRate(simpleTask,2000L,3000L);

        //简单任务，时间未过
        //结果: 2s后执行第一次，之后间隔5s执行
        //timer.scheduleAtFixedRate(longTask,2000L,3000L);

        //简单任务，当前时间超过设定的时间
        //结果: 立即执行4次，之后按照原来的周期执行，两秒后继续执行，然后间隔3s执行
        //timer.scheduleAtFixedRate(simpleTask,cal.getTime(),3000L);

        //复杂任务，当前时间超过设定时间
        //结果: 立即执行第一次，之后每5s执行一次
        //timer.scheduleAtFixedRate(longTask,cal.getTime(),3000L);
    }
}
