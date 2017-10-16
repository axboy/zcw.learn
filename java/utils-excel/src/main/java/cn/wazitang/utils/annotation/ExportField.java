package cn.wazitang.utils.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 作者 zcw
 * 时间 2017/10/14 12:43
 * 描述 TODO
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface ExportField {

    String name();

    ExportType type() default ExportType.STRING;

    String dateFormat() default "yyyy-MM-dd";

    String timezone() default "+08:00";
}
