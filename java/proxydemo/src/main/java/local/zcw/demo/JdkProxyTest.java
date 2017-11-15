package local.zcw.demo;

import local.zcw.demo.jdkproxy.AServiceImpl;
import local.zcw.demo.jdkproxy.IService;
import local.zcw.demo.jdkproxy.MyInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * 作者 zcw
 * 时间 2017/11/14 11:58
 * 版本 1.0.0
 * 描述 TODO
 */
public class JdkProxyTest {
    public static void main(String[] args) {
        IService a = new AServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(a);
        IService proxy = (IService) Proxy.newProxyInstance(
                AServiceImpl.class.getClassLoader(),
                AServiceImpl.class.getInterfaces(),
                handler
        );
        proxy.output();
    }
}
