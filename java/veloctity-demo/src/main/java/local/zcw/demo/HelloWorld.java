package local.zcw.demo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Arrays;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/12/26 12:15
 */
public class HelloWorld {

    public static void main(String[] args) {

        //初始化模板引擎
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        engine.init();

        //取得模板
        Template template = engine.getTemplate("helloworld.vm");

        //取得上下文，并填充数据
        VelocityContext context = new VelocityContext();
        context.put("name", "zcw");
        context.put("list", Arrays.asList("str1", null, "str2"));

        //输出流
        StringWriter writer = new StringWriter();

        //输出转换
        template.merge(context, writer);
        System.out.println(writer.toString());
    }
}
