package local.zcw.demo.cron.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/4/25 15:47
 */
public class HelloJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.printf("Hello world at %s\n", new Date());
    }
}
