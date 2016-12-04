package cn.wazitang.demo1.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zcw on 2016/12/03.
 */
@Configuration          //声明配置类
public class JavaConfig {

    @Bean       //@Bean注解声明当前方法的返回值是一个Bean，Bean的名称是方法名
    public FuncService funcService() {
        return new FuncService();
    }

    @Bean
    public UseFuncService useFuncService() {
        UseFuncService useFuncService = new UseFuncService();
        useFuncService.setFuncService(funcService());
        return useFuncService;
    }
}
