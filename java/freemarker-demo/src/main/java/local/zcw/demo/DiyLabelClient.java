package local.zcw.demo;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/12/26 18:05
 */
public class DiyLabelClient {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/"));
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("UTF-8");

        configuration.setSharedVariable("diyLabel",new DiyLabelDirective());
        Template template = configuration.getTemplate("template-diy.txt");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "zcw");

        Writer writer = new OutputStreamWriter(new FileOutputStream("src/main/resources/out-diy.txt"), "UTF-8");
        template.process(params, writer);
        System.out.println("success");
    }
}
