package pages;

import pages.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest;
import utilities.WaitUtility;
//import utilities.DriverFactory;


public class LoginPage {
    //WebDriver driver;
    WaitUtility waitUtility;       
	Logger logger = LogManager.getLogger(LoginPage.class);
  
    //locators
    @FindBy(id = "user-name")
	WebElement username;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(id = "login-button")
	WebElement Login;
//	@FindBy(xpath="//h3[@data-test='error']")
//	WebElement errorMessage;

	
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        waitUtility = new WaitUtility(driver);
   }
	 

    public void login(String user, String pass){
	    	waitUtility.waitForVisibility(username);
	        username.sendKeys(user);
            logger.info("Entered Username");
	        password.sendKeys(pass);
	        logger.info("Entered Password");
	        Login.click();
	        logger.info("Clicked Login Button");
	}
    

	
	
}
