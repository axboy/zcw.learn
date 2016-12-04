package cn.wazitang.demo1.aop;

import org.springframework.stereotype.Service;

/**
 * Created by zcw on 2016/12/04.
 */
@Service
public class DemoAnnotationService {

    @Action(name = "注解式拦截的save操作")
    public void save() {
    }
}
