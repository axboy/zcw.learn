package cn.axboy.demo.dubbo.hello.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/5/24 11:24
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/hello-dubbo-provider.xml"});
        context.start();
        System.in.read(); // press any key to exit
    }
}
