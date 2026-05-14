package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	static ExtentReports extent;
	
	public static ExtentReports getReport() {
		
		String reportPath = System.getProperty("user.dir") +"/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		
		reporter.config().setReportName("Automation Test Results");
		reporter.config().setDocumentTitle("Selenium Framewrok Report");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Name");
		
		return extent;
		
	}

}
