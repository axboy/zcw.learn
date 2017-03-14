package local.zcw.validate;

import java.lang.reflect.Field;

/**
 * Created by zcw on 17/3/14.
 */
public class MyCheck {
    public static boolean check(MyClass myClass) {
        if (myClass == null) {
            System.err.println("对象为空");
            return false;
        }

        //获取所有属性
        //Field[] fields = MyClass.class.getFields();   //无法获取到私有变量
        Field[] fields = MyClass.class.getDeclaredFields();
        for (Field field : fields) {
            //如果属性有指定注解，就进行校验
            if (field.isAnnotationPresent(Validate.class)) {
                Validate validate = field.getAnnotation(Validate.class);
                if (field.getName().equals("str")) {
                    if(myClass.getStr().length() < validate.min()){
                        System.err.println("最小长度校验失败");
                        return false;
                    }
                    if(myClass.getStr().length() > validate.max()){
                        System.err.println("最长长度校验失败");
                        return false;
                    }
                    //todo other check
                }
            }
        }
        return true;
    }
}
