package local.zcw.demo;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2017/12/26 17:07
 */
public class DiyLabelDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        if (templateDirectiveBody == null) {
            throw new RuntimeException("body == null");
        }
        Writer writer = environment.getOut();
        TemplateModel paramValue = (TemplateModel) map.get("diyStr");
        String str = paramValue.toString();
        writer.write("the diyStr of diy label is " + str);
        templateDirectiveBody.render(environment.getOut());
    }
}
