package local.zcw.demo.cron.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/4/25 15:45
 */
public class HelloQuartz {
    public static void main(String[] args) throws SchedulerException {

        //创建JobDetail实例
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob","group")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger","group")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()
                ).build();
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
