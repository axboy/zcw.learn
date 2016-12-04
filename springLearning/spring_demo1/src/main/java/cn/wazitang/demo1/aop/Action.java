package cn.wazitang.demo1.aop;

import java.lang.annotation.*;

/**
 * Created by zcw on 2016/12/04.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
