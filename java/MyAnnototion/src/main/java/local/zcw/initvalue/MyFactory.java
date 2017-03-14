package local.zcw.initvalue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zcw on 17/3/14.
 */
public class MyFactory {
    public static MyClass create(){
        MyClass myClass = new MyClass();
        Method[] methods = MyClass.class.getMethods();
        for(Method m : methods){
            if(m.isAnnotationPresent(Init.class)){
                Init init = m.getAnnotation(Init.class);
                try {
                    m.invoke(myClass,init.value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return myClass;
    }
}
