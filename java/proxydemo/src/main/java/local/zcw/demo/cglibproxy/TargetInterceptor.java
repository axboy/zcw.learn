package local.zcw.demo.cglibproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 作者 zcw
 * 时间 2017/11/14 12:50
 * 版本 1.0.0
 * 描述 目标对象拦截器
 */
public class TargetInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object object, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        System.out.println("before=============");
        Object result = methodProxy.invokeSuper(object, params);
        System.out.println("after =============");
        return result;
    }
}
