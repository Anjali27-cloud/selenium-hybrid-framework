package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pages.LoginPage;

public class ScreenshotUtility {
	
	static Logger logger = LogManager.getLogger(ScreenshotUtility.class);


	public static String captureScreenshot(WebDriver driver, String testName) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

		String fileName = testName + "_" + timestamp + ".png";

		// Absolute path
		String absolutePath = System.getProperty("user.dir") + "/screenshots/" + fileName;

		File destination = new File(absolutePath);

		try {

			FileUtils.copyFile(source, destination);

			logger.info("Screenshot Captured");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return destination.getAbsolutePath();
	}

}
