package local.zcw.demo.staticproxy;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/11/24 12:28
 */
public class HelloProxy implements Hello {

    private Hello hello;

    public HelloProxy() {
        this.hello = new HelloImpl();
    }

    @Override
    public void say() {
        before();
        hello.say();
        after();
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }
}
