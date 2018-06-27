package util.angular;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForHTTPCallsOver {
	 public static void untilAngularFinishHttpCalls(WebDriver wdObj) {
	        final String javaScriptToLoadAngular =
	                "var injector = window.angular.element('body').injector();" + 
	                "var $http = injector.get('$http');" + 
	                "return ($http.pendingRequests.length === 0)";

	        ExpectedCondition<Boolean> pendingHttpCallsCondition = new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver _driver) {
	                return ((JavascriptExecutor) _driver).executeScript(javaScriptToLoadAngular).equals(true);
	            }
	        };
	        WebDriverWait wait = new WebDriverWait(wdObj, 20); // timeout = 20 secs
	        wait.until(pendingHttpCallsCondition);
	    }
}
