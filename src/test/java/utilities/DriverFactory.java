package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	static ConfigReader config;
	static Logger logger = LogManager.getLogger();
	public static WebDriver initDriver() {
		config = new ConfigReader();
		String browser = config.getBrowser();
		switch (browser.toLowerCase()) {

		case "chrome":
			driver.set(new ChromeDriver());
			;
			logger.info("chrome Browser Launched");
			break;
		case "edge":
			driver.set(new EdgeDriver());
			;
			logger.info("Edge Browser Launched");
			break;
		case "firefox":
			driver.set(new FirefoxDriver());
			logger.info("Firefox Browser Launced");
			break;
		default:
			throw new RuntimeException("Invalid browser");

		}

		getDriver().manage().window().maximize();
		return driver.get();

	}

	public static WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver.get();
	}

}