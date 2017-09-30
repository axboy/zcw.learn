package local.demo.zcw.conf;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * 作者 zcw
 * 时间 2017/9/28 14:59
 * 描述 schedule配置
 */
@Configuration
@EnableScheduling
public class TaskConf implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //todo something
    }
}
