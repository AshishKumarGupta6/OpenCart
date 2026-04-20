package DataProvider;

import ExcelUtilPack.ExcelUtil;
import java.io.IOException;


public class DataDrivenProcess {


    public static Object[][] getExcelData(String excelPath, String sheetName) throws IOException {


        int lastRowIndex = ExcelUtil.getRowCount(excelPath, sheetName);
        Object[][] data = new Object[lastRowIndex][6];
        for (int i = 1; i <= lastRowIndex; i++) {
            for (int j = 0; j < 6; j++) {
                data[i-1][j] = ExcelUtil.getCellData(excelPath, sheetName, i, j);
            }
        }
        return data;
    }
}
