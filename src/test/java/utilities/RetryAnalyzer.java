package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int currentRetry = 0;
	int maxRetry = 1;
	
	public boolean retry(ITestResult result) {
		
		if(currentRetry < maxRetry) {
			currentRetry++;
			System.out.println("Retrying test :" +result.getName() + "Retry count : " +currentRetry);
			return true;
		}
		return false;
		
	}
	
}
