package local.zcw.demo;

import local.zcw.demo.proxychain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/11/24 15:36
 */
public class ProxyChainTest {
    public static void main(String[] args) {
        List<MyProxy> proxyList = new ArrayList<>();
        proxyList.add(new BeforeProxy());
        proxyList.add(new AfterProxy());
        proxyList.add(new AfterProxy());
        ProxyManager proxyManager = new ProxyManager(TestObject.class, proxyList);
        TestObject proxyObject = proxyManager.createProxy();
        proxyObject.testOutPut();
    }
}
