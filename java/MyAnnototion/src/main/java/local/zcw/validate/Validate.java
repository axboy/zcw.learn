package local.zcw.validate;

import java.lang.annotation.*;

/**
 * Created by zcw on 17/3/14.
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
    public int min() default 3;
    public int max() default 10;
    public boolean notNull() default true;
}
