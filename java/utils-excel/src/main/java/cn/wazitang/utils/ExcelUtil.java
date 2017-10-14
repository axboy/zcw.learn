package cn.wazitang.utils;

import cn.wazitang.utils.annotation.ExportField;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者 zcw
 * 时间 2017/10/14 12:46
 * 描述 TODO
 */
public class ExcelUtil {

    public static void createExcel(OutputStream outputStream, String sheetName, List data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, sheetName);

        //表头
        List<String> fieldNames = new ArrayList<String>();

        final Field[] declaredFields = data.get(0).getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            final ExportField annotation = declaredField.getAnnotation(ExportField.class);
            if (annotation != null) {
                fieldNames.add(annotation.name());
            }
        }

        //列宽
        int[] colWidths = new int[fieldNames.size()];
        XSSFCell cell;
        for (int i = 0; i < colWidths.length; i++) {
            cell = getCell(sheet, 0, i);
            cell.setCellValue(fieldNames.get(i));
            //cell.setCellStyle(createDefaultDataStyle(workbook));
            colWidths[i] = fieldNames.get(i).length();
        }
        for (int i = 0; i < data.size(); i++) {
            final Object bean = data.get(i);   //具体每一行数据
            int normalWidth = 0;
            for (int j = 0; j < declaredFields.length; j++) {
                final Field field = declaredFields[j];
                final ExportField[] exportFields = field.getAnnotationsByType(ExportField.class);
                if (exportFields == null || exportFields.length == 0) {
                    //跳过未使用@ExportField注解的域
                    continue;
                }
                final ExportField exportField = exportFields[0];    //当前域上的注解
                cell = getCell(sheet, i + 1, normalWidth++);
                String value = "";
                switch (exportField.type()) {
                    case URL:
                    case NUM:
                    case DATE:
                        String format = exportField.format();
                        String timezone = exportField.timezone();
                        final CellStyle dateStyle = getCellDateStyle(workbook, format);
                        if (field.getType() == Long.class) {
                            final String pattern = exportField.format();
                            final long timestap = -1L;      //TODO
                            DateTime dateTime = new DateTime(timestap, DateTimeZone.forID(timezone));
                            DateTime dateTime1 = DateTime.parse(dateTime.toString(pattern), DateTimeFormat.forPattern(pattern));
                            cell.setCellStyle(dateStyle);
                            cell.setCellValue(dateTime1.toDate());
                        }
                    case STRING:
                    default:
                        //cell.setCellStyle();
                        value = getValue(bean, field);
                        cell.setCellValue(value);
                        //TODO
                }


            }
        }
    }

    /**
     * 获取一个单元格
     */
    private static XSSFCell getCell(XSSFSheet sheet, int row, int col) {
        XSSFRow sheetRow = sheet.getRow(row);
        if (sheetRow == null) {
            sheetRow = sheet.createRow(row);
        }
        XSSFCell cell = sheetRow.getCell(col);
        if (cell == null) {
            cell = sheetRow.createCell(col);
        }
        return cell;
    }

    private static String getValue(Object bean, Field field) {
        //TODO
        return null;
    }

    private static CellStyle getCellDateStyle(XSSFWorkbook workbook, String pattern) {

        XSSFDataFormat format = workbook.createDataFormat();

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);   //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);     //左边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);    //右边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);      //上边框
        cellStyle.setDataFormat(format.getFormat(pattern));

        cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        return cellStyle;
    }
}
