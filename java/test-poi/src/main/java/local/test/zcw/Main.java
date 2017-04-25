package local.test.zcw;

import local.test.zcw.util.ExcelUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcw on 2017/03/28.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        List<String> head = new ArrayList<String>();
        head.add("head1");
        head.add("head2");
        head.add("head3");
        head.add("head4");
        head.add("head5");

        List<List<String>> content = new ArrayList<List<String>>();
        int rowNum = 1;
        while (rowNum < 100) {
            List<String> row = new ArrayList<String>();
            row.add(rowNum + "row1");
            row.add(rowNum + "row2");
            row.add(rowNum + "row3");
            row.add(rowNum + "row4");
            row.add(rowNum + "row5");
            content.add(row);
            rowNum++;
        }
        File file = new File("src/main/resources/test.xls");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        ExcelUtil.createExcel(out, head, content);
        System.out.println("--------写入结束");
    }
}
