package local.zcw.demo.cron.timer;

import java.util.Date;
import java.util.TimerTask;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/4/16 16:04
 * 一个简单的定时任务
 */
public class MySimpleTimerTask extends TimerTask {

    private String taskName;


    public MySimpleTimerTask(String taskName) {
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
    }
}
