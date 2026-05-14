package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

	//private WebDriver driver;
	WebDriverWait wait;

	public WaitUtility(WebDriver driver) {
	    //System.out.println("Driver received in WaitUtility = " + driver);

		//this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Wait initialized is : " + wait);
	}


	public void waitForVisibility(WebElement element) {

		//System.out.println("Wait object = " + wait);

		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitClickability(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

}
