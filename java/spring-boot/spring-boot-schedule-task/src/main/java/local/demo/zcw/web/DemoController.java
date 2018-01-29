package local.demo.zcw.web;

import local.demo.zcw.job.DemoJob;
import local.demo.zcw.job.DemoJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/1/29 16:19
 */
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private DemoJobService demoJobService;

    @RequestMapping("/start")
    public String start() throws SchedulerException {
        demoJobService.startJobs();
        return "Success";
    }

    @RequestMapping("/stop")
    public String stop() throws SchedulerException {
        demoJobService.stopJobs();
        return "Success";
    }

    @RequestMapping("/add")
    public String testAddJob(String jobName, String groupName, String cron, String param) throws SchedulerException {
        DemoJob job = new DemoJob(jobName, groupName, cron);
        demoJobService.addJob(job, param);
        return "Success";
    }

    @RequestMapping("/del")
    public String testDelJob() {
        return "Success";
    }
}
