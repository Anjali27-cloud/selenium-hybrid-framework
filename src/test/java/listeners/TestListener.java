package listeners;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import utilities.ScreenshotUtility;
import utilities.DriverFactory;
import utilities.ExtentManager;

public class TestListener implements ITestListener {
	
	ThreadLocal<ExtentTest> eTest = new ThreadLocal<>();
	ExtentReports extent = ExtentManager.getReport();
	Logger logger = LogManager.getLogger(TestListener.class);

	@Override
	public void onStart(ITestContext context) {
		logger.info("Execution started");

	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
		
		logger.info("Report generated");

		logger.info("Execution completed");
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName() + "-" +result.getParameters()[0];
        System.out.println("testName is :" +testName);
		ExtentTest test = extent.createTest(testName);
		eTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		eTest.get().log(Status.PASS, "Test Passed");
		eTest.get().pass("Test passed sucessfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {

        WebDriver driver = DriverFactory.getDriver();
        String screenshotPath = ScreenshotUtility.captureScreenshot(driver, result.getName());
        eTest.get().fail(result.getThrowable());

		eTest.get().fail(MediaEntityBuilder.createScreenCaptureFromPath(new File(screenshotPath).toURI().toString()).build());
		
		eTest.get().log(Status.FAIL, "Test Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped :" + result.getName());
		eTest.get().skip("Test Skipped");
	}
}
