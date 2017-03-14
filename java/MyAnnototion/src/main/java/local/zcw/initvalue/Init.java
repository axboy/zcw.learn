package local.zcw.initvalue;

import java.lang.annotation.*;

/**
 * Created by zcw on 17/3/14.
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Init {
    public String value() default "";
}
