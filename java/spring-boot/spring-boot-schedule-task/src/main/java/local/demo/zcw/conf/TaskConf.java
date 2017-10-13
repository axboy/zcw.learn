package local.demo.zcw.conf;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

/**
 * 作者 zcw
 * 时间 2017/9/28 14:59
 * 描述 schedule配置
 */
@Configuration
@EnableScheduling
public class TaskConf implements SchedulingConfigurer {

    private static final Logger log = LoggerFactory.getLogger(SchedulingConfigurer.class);

    private static String cron = "0/5 * * * * ?";

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        Runnable runnable = () -> {
            log.info("test cron task");
        };

        Trigger trigger = (triggerContext) -> {
            CronTrigger cronTrigger = new CronTrigger(cron);
            return cronTrigger.nextExecutionTime(triggerContext);
        };

        scheduledTaskRegistrar.addCronTask(runnable, cron);         //不可更改

        scheduledTaskRegistrar.addTriggerTask(runnable, trigger);   //可修改trigger的值

        //scheduledTaskRegistrar.setCronTasksList(null);
        //scheduledTaskRegistrar.setFixedDelayTasksList(null);
        //scheduledTaskRegistrar.setFixedRateTasksList(null);
    }
}
