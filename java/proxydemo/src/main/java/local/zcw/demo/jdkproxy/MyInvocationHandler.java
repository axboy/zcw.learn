package local.zcw.demo.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 作者 zcw
 * 时间 2017/11/14 12:02
 * 版本 1.0.0
 * 描述 TODO
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target){
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before output---------");
        Object result = method.invoke(target, args);
        System.out.println("after  output---------");
        return result;
    }
}
