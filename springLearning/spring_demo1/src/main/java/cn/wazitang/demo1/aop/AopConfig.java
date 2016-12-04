package cn.wazitang.demo1.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by zcw on 2016/12/04.
 */
@Configuration
    @ComponentScan("cn.wazitang.demo1.aop")
    @EnableAspectJAutoProxy     //开启Spring对AspectJ的支持
    public class AopConfig {

}
