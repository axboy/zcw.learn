package cn.wazitang.utils.annotation;

/**
 * 作者 zcw
 * 时间 2017/10/14 12:43
 * 描述 TODO
 */
public @interface ExportField {

    String name();

    ExportType type() default ExportType.STRING;

    String format() default "yyyy-MM-dd";

    String timezone() default "+8:00";
}
