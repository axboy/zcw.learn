package cn.wazitang.demo1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zcw on 2016/12/04.
 */
@Aspect         //声明一个切面
@Component      //使该切面成为spring容器管理的bean
public class LogAspect {

    //通过@Pointcut声明切点
    @Pointcut("@annotation(cn.wazitang.demo1.aop.Action)")
    public void annotationPointCut() {
    }

    //
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截" + action.name());
    }

    //TODO 下行待查
    @Before("execution(cn.wazitang.demo1.aop.DemoMethodService.save)")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则式拦截" + method.getName());
    }
}
