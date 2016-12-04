package cn.wazitang.demo1.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zcw on 2016/12/03.
 */
@Configuration      //声明当前类是配置类
@ComponentScan("cn.wazitang.demo1.di") //自动扫描包下所有的@service、@Component、@Repository、@Controller，并注册为ben
public class DiConfig {
}
