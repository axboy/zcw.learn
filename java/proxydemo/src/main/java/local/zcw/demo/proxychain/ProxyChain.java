package local.zcw.demo.proxychain;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/11/24 12:51
 */
public class ProxyChain {

    private List<MyProxy> proxyList;
    private int currentProxyIndex = 0;

    private Class<?> targetClass;
    private Object targetObject;
    private Method targetMethod;
    private Object[] methodParams;
    private MethodProxy methodProxy;
    private Object methodResult;

    public ProxyChain(Class<?> targetClass,
                      Object targetObject,
                      Method targetMethod,
                      Object[] methodParams,
                      MethodProxy methodProxy,
                      List<MyProxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodParams = methodParams;
        this.methodProxy = methodProxy;
        this.proxyList = proxyList;
    }

    public void doProxyChain() {
        if (currentProxyIndex < proxyList.size()) {
            proxyList.get(currentProxyIndex++).doProxy(this);
        } else {
            try {
                methodResult = methodProxy.invokeSuper(targetObject, methodParams);
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        }
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public Object getMethodResult() {
        return methodResult;
    }

}
