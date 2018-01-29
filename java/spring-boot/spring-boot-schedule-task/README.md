# Spring boot 定时任务学习

### @Scheduled 注解说明

- @Scheduled(fixedRate = 5 * 1000) ：上一次开始执行时间点之后5秒再执行

- @Scheduled(fixedDelay = 5 * 1000) ：上一次执行完毕时间点之后5秒再执行

- @Scheduled(initialDelay=1000, fixedRate=5 * 1000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次

- @Scheduled(cron="*/10 */1 * * * ?") ：通过cron表达式定义规则

### 额外控制

    实现SchedulingConfigurer接口
    //todo
    
### quartz学习
    TODO

### 参考资料

- [https://spring.io/guides/gs/scheduling-tasks/](https://spring.io/guides/gs/scheduling-tasks/)

- [Spring @Scheduled定时任务的fixedRate,fixedDelay,cron执行差异](http://www.cnblogs.com/myibm/p/7068590.html)