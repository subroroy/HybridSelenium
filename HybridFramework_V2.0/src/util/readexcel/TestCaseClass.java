package util.readexcel;

import java.util.LinkedList;

public class TestCaseClass {
	private LinkedList<TestStepClass> testCase = new LinkedList<TestStepClass>();
	private String testCaseName;
	
	public String toString(){
		return this.testCaseName;
	}
	/*TestCaseClass(){
		this.testCase = new LinkedList<TestStepClass>();
	}*/

	public LinkedList<TestStepClass> getTestCase() {
		return testCase;
	}

	public void setTestCase(LinkedList<TestStepClass> testCase) {
		this.testCase = testCase;
	}

	public void addTestStep(TestStepClass testStepObject) {
		this.testCase.add(testStepObject);

	}

	public TestStepClass getTestStep(int index) throws ArrayIndexOutOfBoundsException {
		return this.testCase.get(index);
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
}
