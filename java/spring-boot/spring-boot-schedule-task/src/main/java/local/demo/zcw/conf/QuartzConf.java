package local.demo.zcw.conf;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 作者 zcw
 * 时间 2017/9/13 16:36
 * 描述 Quartz相关的bean
 */
@Configuration
public class QuartzConf {

    @Bean
    @Scope("singleton")
    public SchedulerFactory schedulerFactory() {
        return new StdSchedulerFactory();
    }

    @Bean
    @Scope("singleton")
    public Scheduler scheduler(SchedulerFactory schedulerFactory) throws SchedulerException {
        return schedulerFactory.getScheduler();
    }
}
