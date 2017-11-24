package local.zcw.demo.staticproxy;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/11/24 12:27
 */
public class HelloImpl implements Hello {
    @Override
    public void say() {
        System.out.println("hello");
    }
}
