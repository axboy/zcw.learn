package local.demo.zcw.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 作者 zcw
 * 时间 2017/9/28 11:21
 * 描述 测试定时任务
 */
@Component
public class TestScheculeTask {

    private static final Logger log = LoggerFactory.getLogger(TestScheculeTask.class);

    @Scheduled(cron = "*/10 */1 * * * ?", zone = "Asia/Shanghai")
    public void cronTask() {
        log.info("test schedule task start...");
    }
}
