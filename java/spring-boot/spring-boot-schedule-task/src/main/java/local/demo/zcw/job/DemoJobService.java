package local.demo.zcw.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/1/29 16:10
 */
@Service
public class DemoJobService {

    @Autowired
    private SchedulerFactory schedulerFactory;

    @Autowired
    private Scheduler scheduler;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoJobService.class);

    public void addJob(DemoJob job, String param) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(job.jobName, job.groupName);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.cronExpression);

        if (trigger != null) {
            LOGGER.info("job Exist,{}-{}", job.jobName, job.groupName);
            // Trigger已存在，那么更新相应的定时设置,按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            return;
        }

        // 不存在，创建一个
        JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(job.jobName, job.groupName).build();
        jobDetail.getJobDataMap().put("param", param);
        trigger = TriggerBuilder.newTrigger().withIdentity(job.jobName, job.groupName).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 移除任务
     *
     * @param job
     */
    public void removeJob(DemoJob job) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(job.jobName, job.groupName);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        scheduler.pauseTrigger(triggerKey);          //停止触发器
        scheduler.unscheduleJob(triggerKey);         //移除触发器
        scheduler.deleteJob(trigger.getJobKey());    //删除任务
    }

    /**
     * 启动全部任务
     */
    public void startJobs() throws SchedulerException {
        scheduler.start();
    }

    /**
     * 关闭任务
     */
    public void stopJobs() throws SchedulerException {
        scheduler.shutdown();
    }
}
