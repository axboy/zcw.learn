package local.zcw.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zcw on 2/8/17.
 * Spring MVC的配置
 */
@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/virtual_wx").setViewName("virtual_wx");
        registry.addViewController("/choose").setViewName("choose");
    }
}
