package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static Object[][] getExcelData(String filePath) {

	    Object[][] data = null;

		try {
			FileInputStream fis = new FileInputStream(filePath);

			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheetAt(0);

			int totalRows = sheet.getLastRowNum();
			
			int totalCols = sheet.getRow(0).getLastCellNum();
			
			data = new Object[totalRows][totalCols];
			
			DataFormatter formatter = new DataFormatter();
			
			System.out.println("Last Row Num : "+ sheet.getLastRowNum());

			for (int i = 1; i <= sheet.getLastRowNum() ; i++) {

				Row row = sheet.getRow(i);
								
				for (int j = 0; j < totalCols; j++) {
					
				String value = formatter.formatCellValue(row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));

				data[i-1][j] = value;		
				
				System.out.println("Saved data is :" + value);

				}
			}

			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

}
