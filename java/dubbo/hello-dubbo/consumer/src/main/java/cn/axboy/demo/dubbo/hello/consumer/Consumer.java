package cn.axboy.demo.dubbo.hello.consumer;

import cn.axboy.demo.dubbo.hello.api.IDemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/5/24 11:24
 */
public class Consumer {
    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/hello-dubbo-consumer.xml"});
        context.start();
        IDemoService demoService = (IDemoService) context.getBean("demoService"); // get remote service proxy

        while (true) {
            try {
                Thread.sleep(1000);
                String hello = demoService.sayHello("world");
                System.out.println(hello); // get result
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
