package local.zcw.demo;

import local.zcw.demo.cglibproxy.TargetInterceptor;
import local.zcw.demo.cglibproxy.TargetObject;
import net.sf.cglib.proxy.Enhancer;

/**
 * 作者 zcw
 * 时间 2017/11/14 12:53
 * 版本 1.0.0
 * 描述 TODO
 */
public class CgLibTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(new TargetInterceptor());
        TargetObject target = (TargetObject)enhancer.create();
        target.output();
    }
}
