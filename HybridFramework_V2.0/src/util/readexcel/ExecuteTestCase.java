package util.readexcel;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.asserts.Assertion;

public class ExecuteTestCase extends Keyword{
	private WebDriver webDriverObject;
	public static Logger Log ;
	
	public WebDriver getWebDriverObject() {
		return webDriverObject;
	}

	public void setWebDriverObject(WebDriver webDriverObject) {
		this.webDriverObject = webDriverObject;
	}

	ExecuteTestCase(){
		System.setProperty("webdriver.chrome.driver",
				new ReadPropertiesFile().getValue("ChromeDriverPath"));
	}
	public void setWebDriverObject(WebDriver webDriverObject, String url) {
		this.webDriverObject.get(url);
		this.webDriverObject.manage().timeouts().pageLoadTimeout(-100, TimeUnit.SECONDS);
		this.webDriverObject = webDriverObject;
	}

	public static void main(String[] args) {
		
		//Initialize test suite
		//Log = Logger.getLogger(Log.getClass().getName());
		DOMConfigurator.configure("log4j.xml");
		CreateTestSuite objCreateTestSuite = new CreateTestSuite();
		LinkedList<TestCaseClass> testSuite;
		testSuite = objCreateTestSuite.createTestSuite();
		objCreateTestSuite.displayTuple(testSuite);
		
		//Declare test case
		TestCaseClass testCase;
		
		//Declare test step
		TestStepClass testStep;

		//Run Test suite
		for(int i=0;i<testSuite.size();i++){
			System.out.println("test case " + i+1);
			testCase=testSuite.get(i);
			ExecuteTestCase exeTCObject = new ExecuteTestCase();
			Reporter.log("\n***Test Case : " + testCase.getTestCaseName());
			//WebDriver wdObj = new ChromeDriver();
			for(int j=0;j<testCase.getTestCase().size();j++){
				System.out.println("test step " + j);
				testStep=testCase.getTestStep(j);
				
				if(j==0){
					exeTCObject.setWDObj(new ChromeDriver(), testStep.getData());
					//wdObj.get(testStep.getData());
					Reporter.log("\nStep " + j + ": " + testStep);
				}
				else{
					try {
						exeTCObject.performAction(testStep, exeTCObject.getWDObj());
					} catch (NumberFormatException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//wdObj.close();
			exeTCObject.getWDObj().close();
		}
	}
}
