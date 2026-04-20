package ExcelUtilPack;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {
    public static FileInputStream file;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static FileOutputStream fileOut;
    public static XSSFCellStyle style;
    public static XSSFCell cell;
    public static XSSFRow row;

    public static int getRowCount(String xPath, String xsheet) throws IOException {
        file = new FileInputStream(xPath);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(xsheet);
        int rowsCount = sheet.getLastRowNum();
        workbook.close();
        file.close();
        return rowsCount;

    }

    public static String getCellData(String xPath, String xsheet, int rowIndex, int cellIndex) throws IOException {
        file = new FileInputStream(xPath);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(xsheet);
        row = sheet.getRow(rowIndex);
        cell = row.getCell(cellIndex);
        DataFormatter format = new DataFormatter();
        String input;
        if (cell != null) {
            input = format.formatCellValue(cell);
        } else {
            input = "";
        }

        workbook.close();
        file.close();
        return input;
    }

    public static void setCellData(String path, String xlsheet, int rownum, int colnum, String data) throws IOException {
        file = new FileInputStream(path);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(xlsheet);

        if (sheet == null) {
            sheet = workbook.createSheet(xlsheet);
        }
        if (sheet.getRow(rownum) == null) {
            row = sheet.createRow(rownum);
        } else {
            row = sheet.getRow(rownum);
        }
        if (row.getCell(colnum) == null) {
            cell = row.createCell(colnum);
        } else {
            cell = row.getCell(colnum);
        }
        if (data.equals("")) {
            cell.setCellValue("null");
        } else {
            cell.setCellValue(data);
        }
        fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();
        file.close();
    }


    public static void setGreenColor(String xPath, String xsheet, int rowIndex, int cellIndex) throws IOException {
        file = new FileInputStream(xPath);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(xsheet);
        row = sheet.getRow(rowIndex);
        cell = row.getCell(cellIndex);
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        file.close();
        fileOut = new FileOutputStream(xPath);
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();


    }


    public static void setRedColor(String xPath, String xsheet, int rowIndex, int cellIndex) throws IOException {
        file = new FileInputStream(xPath);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(xsheet);
        row = sheet.getRow(rowIndex);
        cell = row.getCell(cellIndex);
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        file.close();
        fileOut = new FileOutputStream(xPath);
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();


    }

}
