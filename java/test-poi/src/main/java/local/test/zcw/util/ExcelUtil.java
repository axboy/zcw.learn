package local.test.zcw.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by zcw on 2017/03/28.
 */
public class ExcelUtil {

    private final static int SHEET_SIZE = 50000;

    /**
     * 创建excel
     *
     * @param out
     * @param head    ;excel头部，允许为空
     * @param content ;内容
     * @return
     */
    public static OutputStream createExcel(OutputStream out, List<String> head, List<List<String>> content) {
        int count = 0;
        int size = content.size();
        HSSFWorkbook workbook = new HSSFWorkbook();
        while (true) {
            HSSFSheet sheet = workbook.createSheet();
            int rowNum = 0;
            if (head != null) {
                putOneRowInExcel(sheet, head, 0);
                rowNum = 1;
            }
            int last = rowNum + SHEET_SIZE;
            //当不是每一个sheet的最后一条，并且不是最后一条数据
            while (rowNum < last && count < size) {
                putOneRowInExcel(sheet, content.get(count), rowNum);
                rowNum++;
                count++;
            }
            if (count == size) {
                break;
            }
        }
        try {
            workbook.write(out);
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * 插入一行数据到excel
     *
     * @param sheet
     * @param list   ;每一列的数据,[xxx,xx,xxx]
     * @param rowNum ;行号，从0开始
     * @return
     */
    public static HSSFSheet putOneRowInExcel(HSSFSheet sheet, List<String> list, int rowNum) {
        HSSFRow row = sheet.createRow(rowNum);
        int col = 0;
        for (String it : list) {
            HSSFCell cell = row.createCell(col);
            cell.setCellValue(it);
            col++;
        }
        return sheet;
    }
}
