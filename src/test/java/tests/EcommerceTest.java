package tests;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.*;
//import io.github.bonigarcia.wdm.WebDriverManager;
import base.BaseTest;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.ScreenshotUtility;
import utilities.TestData;
//import utils.DriverFactory;
import listeners.TestListener;



@Listeners(TestListener.class)

public class EcommerceTest extends BaseTest {
	
	Logger logger = LogManager.getLogger(EcommerceTest.class);

	@Test(dataProvider = "loginData", dataProviderClass = TestData.class, retryAnalyzer = utilities.RetryAnalyzer.class)
	public void openSiteTest(String username, String password, String expectedResult) {

		logger.info("Website opened successfully");

		login(username,password);

		String expectedUrl = "https://www.saucedemo.com/inventory.html";

		String actualUrl = getDriver().getCurrentUrl();

		logger.info("Current url is :" + actualUrl);
		
		//Assert.assertTrue(false);
        if(expectedResult.equalsIgnoreCase("true")) {
        	
		Assert.assertEquals(actualUrl, expectedUrl);
        	//Assert.assertEquals(actualUrl, "Wrong_Url");
        	
		logger.info(" Posivitive validation passed");
        
        } 
        
        else {
           
        	Assert.assertNotEquals(actualUrl, expectedUrl);
        	
            System.out.println("Negitive validation Passed");
          
        }
		
		logger.info("Assertion passed");
		
		System.out.println("Thread ID:" +Thread.currentThread().getId());
		
	}

	public void login(String username, String password) {

		System.out.println("Driver in test = " + getDriver());

		LoginPage loginPage = new LoginPage(getDriver());
		
		//ConfigReader config = new ConfigReader();

		loginPage.login(username, password);		
		
		//Assert.assertTrue(loginPage.isErrorMessageDisplayed());
		
	}

}
