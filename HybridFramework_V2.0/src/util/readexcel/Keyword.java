package util.readexcel;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import util.angular.JSWaiter;
import util.angular.WaitForHTTPCallsOver;

public class Keyword {

	private String action;
	private int errMsg;
	private String locType;
	private String locValue;
	private WebElement WEObj;
	private WebDriver WDObj;
	private Long waitTimeOutinSeconds;

	public Long getWaitTimeOutinSeconds() {
		return waitTimeOutinSeconds;
	}

	public void setWaitTimeOutinSeconds(Long waitTimeOutinSeconds) {
		ReadPropertiesFile rpfObj = new ReadPropertiesFile();
		try {
			Long timeoutValue = Long.parseLong(rpfObj.getValue("waitTimeOutInSeconds"));
			this.waitTimeOutinSeconds = timeoutValue;
		} catch (Exception e) {
			Reporter.log("\nError in parsing timeoutValue : " + e.toString());
		}

	}

	public WebDriver getWDObj() {
		return WDObj;
	}

	public void setWDObj(WebDriver wDObj, String _url) {
		WDObj = wDObj;
		WDObj.manage().window().maximize();
		WDObj.get(_url);
		WDObj.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public WebElement getWebElementObject() {
		return WEObj;
	}

	public void setWebElementObject(WebElement wEObj) {
		WEObj = wEObj;
	}

	public String getLocType() {
		return locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

	public String getLocValue() {
		return locValue;
	}

	public void setLocValue(String locValue) {
		this.locValue = locValue;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(int errMsg) {
		this.errMsg = errMsg;
	}

	public String performAction(TestStepClass tsObj, WebDriver wdObj)
			throws NumberFormatException, InterruptedException {

		//JSWaiter jswObj = new JSWaiter();
		/*JSWaiter.setDriver(wdObj);
		JSWaiter.waitForJQueryLoad();*/
		/*WaitForHTTPCallsOver.untilAngularFinishHttpCalls(wdObj);*/
		setAction(tsObj.getKeyword());
		setLocatorTypeAndValue(tsObj.getLocator(), tsObj.getKeyword());
		if (! getLocType().toLowerCase().equals("na"))
			setWebElementObject(wdObj.findElement(getByObject(getLocType(), getLocValue())));

		switch (getAction().toLowerCase()) {

		case ("wait"):

			Thread.sleep(Long.parseLong(tsObj.getData()));
			return "Success";

		case ("click"):
			waitForElementVisible(wdObj, getWebElementObject());
			return actionClick(wdObj, getWebElementObject());

		case ("sendkeys"):
			//waitForElementVisible(wdObj, getWebElementObject());
			waitForPageLoadComplete(wdObj, getWebElementObject());
			return actionSendKeys(getWebElementObject(), tsObj.getData());

		case ("selectbyindex"):
			return actionSelectByIndex(getWebElementObject(), tsObj.getData());

		case ("selectbyvisibletext"):
			return actionSelectByVisibleText(getWebElementObject(), tsObj.getData());

		case ("selectbyvalue"):
			return actionSelectByValue(getWebElementObject(), tsObj.getData());

		case ("select"):
			return actionSelectByValue(getWebElementObject(), tsObj.getData());
		}
		return "Failure";
	}

	private void waitForElementClickable(WebDriver wdObj, WebElement weObj) {
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		try {
			WebDriverWait wait = new WebDriverWait(wdObj, getWaitTimeOutinSeconds());
			wait.until(ExpectedConditions.elementToBeClickable(weObj));
			stopwatch.stop();
			Reporter.log("\nElement" + getLocType() + "became visible in " + stopwatch.getTime());
		} catch (Exception e) {
			Reporter.log("\nException in waiting for element : " + e.toString());
		}
	}

	private void waitForElementVisible(WebDriver wdObj, WebElement weObj) {
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		try {
			WebDriverWait wait = new WebDriverWait(wdObj, getWaitTimeOutinSeconds());
			wait.until(ExpectedConditions.visibilityOfElementLocated(getByObject(getLocType(), getLocValue())));
			stopwatch.stop();
			Reporter.log("\nElement" + getLocType() + "became visible in " + stopwatch.getTime());
		} catch (Exception e) {
			Reporter.log("\nException in waiting for element : " + e.toString());
		}
	}

	
	
	
	private void waitForPageLoadComplete(WebDriver wdObj, WebElement weObj) {
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    }
	                };
	        WebDriverWait wait = new WebDriverWait(wdObj, 30);
	        wait.until(pageLoadCondition);
			stopwatch.stop();
			Reporter.log("\nElement" + getLocType() + "became visible in " + stopwatch.getTime());
		} catch (Exception e) {
			Reporter.log("\nException in waiting for element : " + e.toString());
		}
	}
	public String actionClick(WebDriver wedDriObj, WebElement webEleObj) {
		//webEleObj.click();
		//Support Ajax
		Actions actions = new Actions(wedDriObj);
		actions.moveToElement(webEleObj).click().build().perform();
		return "Success";
	}

	public String actionSelectByIndex(WebElement webEleObj, String data) {
		Select sel = new Select(webEleObj);
		try {
			sel.selectByIndex(Integer.parseInt(data));
		} catch (Exception e) {
			return "Failure : " + e.getMessage();
		}
		return "Success";
	}

	public String actionSelectByValue(WebElement webEleObj, String data) {
		Select sel = new Select(webEleObj);
		try {
			sel.selectByValue(data);
		} catch (Exception e) {
			return "Failure : " + e.getMessage();
		}
		return "Success";
	}

	public String actionSelectByVisibleText(WebElement webEleObj, String data) {
		Select sel = new Select(webEleObj);
		try {
			sel.selectByVisibleText(data);
		} catch (Exception e) {
			return "Failure : " + e.getMessage();
		}
		return "Success";
	}

	public String actionSendKeys(WebElement webEleObj, String data) {
		webEleObj.sendKeys(data);
		return "Success";
	}

	public void setLocatorTypeAndValue(String locatorValue, String action) {

		if (action.equalsIgnoreCase("wait")) {
			this.locType = "NA";
			this.locValue = "NA";
		} else {
			String locValCaps = locatorValue.toUpperCase();
			if (locValCaps.startsWith("ID") || locValCaps.startsWith("NAME") || locValCaps.startsWith("XPATH")) {
				int indexEquals = locatorValue.indexOf("=");
				this.locType = locatorValue.substring(0, indexEquals - 1);
				this.locValue = locatorValue.substring(indexEquals + 1, locatorValue.length());
			} else {
				this.locType = "xpath";
				this.locValue = locatorValue;
			}
		}

	}

	public By getByObject(String locatorType, String locatorValue) {
		switch (locatorType.toLowerCase()) {
		case ("name"):
			return By.name(locatorValue);
		case ("id"):
			return By.id(locatorValue);
		case ("xpath"):
			return By.xpath(locatorValue);
		default:
			return By.xpath(locatorValue);
		}

	}
}
