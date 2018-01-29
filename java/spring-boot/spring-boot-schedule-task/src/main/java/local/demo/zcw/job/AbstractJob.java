package local.demo.zcw.job;

import org.quartz.Job;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/1/29 15:59
 * 动态创建定时任务基类
 */
public abstract class AbstractJob implements Job {

    String jobName;

    String groupName;

    String cronExpression;
}
