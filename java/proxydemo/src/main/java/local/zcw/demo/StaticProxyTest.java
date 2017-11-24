package local.zcw.demo;

import local.zcw.demo.staticproxy.Hello;
import local.zcw.demo.staticproxy.HelloProxy;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/11/24 12:32
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        Hello hello = new HelloProxy();
        hello.say();
    }
}
