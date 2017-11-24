package local.zcw.demo.proxychain;

import java.lang.reflect.Method;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/11/24 15:21
 */
public class AfterProxy implements MyProxy {

    @Override
    public void doProxy(ProxyChain proxyChain) {
//        Class<?> cls = proxyChain.getTargetClass();
//        Method method = proxyChain.getTargetMethod();
//        Object[] params = proxyChain.getMethodParams();
        proxyChain.doProxyChain();
        System.out.println("after proxy");
    }
}
