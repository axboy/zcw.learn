package cn.wazitang.utils;

import cn.wazitang.utils.model.TestModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者 zcw
 * 时间 2017/10/14 17:04
 * 描述 TODO
 */
public class Main {
    public static void main(String[] args) throws IOException {
        TestModel model = new TestModel();
        List<TestModel> list = new ArrayList<>(5);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        File file = new File("src/main/resources/test.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        ExcelUtil.createExcel(out, "ItsSheetName", list);
        out.close();
        out.flush();
    }
}
