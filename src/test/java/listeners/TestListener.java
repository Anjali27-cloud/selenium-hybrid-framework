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

import base.BaseTest;
import pages.LoginPage;
import utilities.ScreenshotUtility;
import utilities.ExtentManager;

public class TestListener implements ITestListener {

	ExtentReports extent = ExtentManager.getReport();
	ExtentTest eTest;
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
		eTest = extent.createTest(testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		eTest.log(Status.PASS, "Test Passed");
		// System.out.println("Test Passed :" + result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {

		eTest.fail(result.getThrowable());
		// System.out.println("Test Failed :" +result.getName());
		Object currentClass = result.getInstance(); // Get current class object
		BaseTest baseTest = (BaseTest) currentClass; // cast the object to base test
		WebDriver driver = baseTest.driver; // Get the driver from base test

		String screenshotPath = ScreenshotUtility.captureScreenshot(driver, result.getName());

		eTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(new File(screenshotPath).toURI().toString()).build());
		
		eTest.log(Status.FAIL, "Test Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped :" + result.getName());
	}
}
