package base;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import utilities.ConfigReader;

import utilities.DriverFactory;

public class BaseTest {

	public WebDriver driver;
	Logger logger = LogManager.getLogger(BaseTest.class);
	ConfigReader config = new ConfigReader();


	@BeforeMethod
	public void setup() {
		logger.info("SETUP IS RUNNING");
		try {
		driver = DriverFactory.initDriver();
		driver.get(config.getURL());
		logger.info("Browser launched");
		} catch(Exception e) {
			logger.error("Exception occurred", e);
		}
	}

	@AfterMethod
	public void teardown() {
		// System.out.println("Inside teardown");

		System.out.println("Teardown Driver = " + driver);

		if (driver != null) {

			driver.quit();
		}
		
		logger.info("Test Execution Completed Successfully");

	}
}
