package cn.wazitang.demo1.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by zcw on 2016/12/04.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
        demoAnnotationService.save();

        //DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
        //demoMethodService.save();
        context.close();
    }
}
