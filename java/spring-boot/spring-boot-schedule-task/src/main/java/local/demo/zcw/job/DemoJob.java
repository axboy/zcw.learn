package local.demo.zcw.job;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/1/29 16:01
 */
public class DemoJob extends AbstractJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        String param = (String) jobDetail.getJobDataMap().get("param");
        JobKey jobKey = jobDetail.getKey();
        LOGGER.info("jobName: {}, jobGroup: {}, param: {}", jobKey.getName(), jobKey.getGroup(), param);
    }

    public DemoJob(String jobName, String groupName, String cron) {
        this.jobName = jobName;
        this.groupName = groupName;
        this.cronExpression = cron;
    }

    public DemoJob() {
    }
}
