package cn.wazitang.demo1.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by zcw on 2016/12/03.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JavaConfig.class);
        //获得声配置的UseFuncService的Bean
        UseFuncService useFuncService = context.getBean(UseFuncService.class);
        System.out.println(useFuncService.sayHello("zcw"));
        context.close();
    }
}
