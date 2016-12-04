package cn.wazitang.demo1.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by zcw on 2016/12/03.
 */
public class Main {
    public static void main(String[] args) {
        //使用AnnotationConfigApplicationContext作为spring容器，接受输入一个配置类作为参数
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DiConfig.class);
        //获得声配置的UseFuncService的Bean
        UseFuncService useFuncService = context.getBean(UseFuncService.class);
        System.out.println(useFuncService.sayHello("zcw"));
        context.close();
    }
}
