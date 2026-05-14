package utilities;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name = "loginData")
	public Object[][] getLoginData() {
		
		String filePath = System.getProperty("user.dir") + "/testData/LoginData.xlsx";
		
		return ExcelUtility.getExcelData(filePath);
	}
	

}
