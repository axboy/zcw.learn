package cn.wazitang.utils;

import cn.wazitang.utils.annotation.ExportField;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作者 zcw
 * 时间 2017/10/14 12:46
 * 描述 TODO
 */
public class ExcelUtil {

    public static void createExcel(OutputStream outputStream, String sheetName, List data) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, sheetName);

        //表头
        List<String> fieldNames = new ArrayList<>();
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
            final Object bean = data.get(i);    //具体每一行数据
            int curCol = 0;                     //当前列
            for (final Field field : declaredFields) {
                field.setAccessible(true);
                final ExportField[] exportFields = field.getAnnotationsByType(ExportField.class);
                if (exportFields == null || exportFields.length == 0) {
                    //跳过未使用@ExportField注解的域
                    continue;
                }
                final ExportField exportField = exportFields[0];    //当前域上的注解
                cell = getCell(sheet, i + 1, curCol);
                switch (exportField.type()) {
                    case URL:
                        String linkValue = (String) getBeanValue(bean, field);
                        cell.setCellStyle(getCellLinkStyle(workbook));
                        cell.setCellValue(linkValue);
                        colWidths[curCol] = linkValue.length();
                        break;
                    case NUM:
                        Number number = (Number) getBeanValue(bean, field);
                        cell.setCellValue(number.doubleValue());
                        break;
                    case DATE:
                        Long timestamp;
                        if (field.getType() == Date.class) {
                            timestamp = ((Date) getBeanValue(bean, field)).getTime();
                        } else {
                            timestamp = (Long) getBeanValue(bean, field);
                        }
                        String format = exportField.dateFormat();
                        String timezone = exportField.timezone();
                        final String pattern = exportField.dateFormat();
                        final CellStyle dateStyle = getCellDateStyle(workbook, format);
                        DateTime dateTime = new DateTime(timestamp, DateTimeZone.forID(timezone));          //时区转换
                        DateTime dateTime1 = DateTime.parse(dateTime.toString(pattern), DateTimeFormat.forPattern(pattern));
                        cell.setCellStyle(dateStyle);
                        cell.setCellValue(dateTime1.toDate());
                        colWidths[curCol] = format.length();
                        break;
                    case STRING:
                    default:
                        String strValue = getBeanValue(bean, field).toString();
                        cell.setCellValue(strValue);
                        if (strValue.length() > colWidths[curCol]) {
                            colWidths[curCol] = strValue.length();
                        }
                }
                curCol++;
            }
        }

        for (int j = 0; j < fieldNames.size(); j++) {
            double t = (colWidths[j] + 2) * 256;
            if (t > 30 * 256) {
                t = 20 * 256;   //限制最大宽度
            }
            sheet.setColumnWidth(j, (int) t);
        }
        //sheet.setDefaultRowHeightInPoints(20);
        sheet.createFreezePane(0, 1);       //冻结第一行
        workbook.write(outputStream);
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

    /**
     * 对象取值
     */
    private static Object getBeanValue(Object bean, Field field) {
        Object result = null;
        try {
            result = field.get(bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 日期样式
     */
    private static CellStyle getCellDateStyle(XSSFWorkbook workbook, String pattern) {
        XSSFDataFormat format = workbook.createDataFormat();
        CellStyle cellStyle = workbook.createCellStyle();
        setCellBorder(cellStyle);
        cellStyle.setDataFormat(format.getFormat(pattern));
        cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        return cellStyle;
    }

    /**
     * 链接样式
     */
    private static CellStyle getCellLinkStyle(XSSFWorkbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        setCellBorder(cellStyle);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font cellFont = workbook.createFont();
        cellFont.setUnderline((byte) 1);
        cellFont.setColor(HSSFColor.BLUE.index);
        cellStyle.setFont(cellFont);
        return cellStyle;
    }

    /**
     * 设置边框
     */
    private static void setCellBorder(CellStyle cellStyle) {
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);      //上边框
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);   //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);     //左边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);    //右边框
    }
}
