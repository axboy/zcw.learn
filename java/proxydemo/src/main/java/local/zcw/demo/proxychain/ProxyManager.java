package local.zcw.demo.proxychain;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/11/24 15:50
 */
public class ProxyManager {

    private Class<?> targetClass;
    private List<MyProxy> proxyList;

    public ProxyManager(Class<?> targetClass, List<MyProxy> proxyList) {
        this.targetClass = targetClass;
        this.proxyList = proxyList;
    }

    @SuppressWarnings("unchecked")
    public <T> T createProxy() {
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                ProxyChain proxyChain = new ProxyChain(targetClass, target, method, args, methodProxy, proxyList);
                proxyChain.doProxyChain();
                return proxyChain.getMethodResult();
            }
        });
    }
}
