package util.readexcel;
import java.util.LinkedList;

public class CreateTestSuite {

	private int rowCount, colCount;
	private String keyword, locator, data, colour;
	private String testCaseSheetName;

	private LinkedList<LinkedList<TestStepClass>> testSuite;

	public String getTestCaseSheetName() {
		return testCaseSheetName;
	}

	public void setTestCaseSheetName(String testCaseSheetName) {
		this.testCaseSheetName = testCaseSheetName;
	}

	public LinkedList<LinkedList<TestStepClass>> getTestSuite() {
		return testSuite;
	}

	public void setTestSuite(LinkedList<LinkedList<TestStepClass>> testSuite) {
		this.testSuite = testSuite;
	}

	public void addToTestSuite(LinkedList<TestStepClass> testCase) {
		this.testSuite.add(testCase);
	}

	public void addToTestSuite(LinkedList<LinkedList<TestStepClass>> objTestSuite, LinkedList<TestStepClass> testCase) {
		objTestSuite.add(testCase);
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public void setColCount(int colCount) {
		this.colCount = colCount;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void displayTuple(LinkedList<TestCaseClass> testSuite) {
		for (TestCaseClass tCase : testSuite) {
			System.out.println("test case " + tCase.getTestCaseName());
			for (int j = 0; j < tCase.getTestCase().size(); j++) {
				System.out.println("test step " + j + 1);
				System.out.println(tCase.getTestStep(j));
			}
		}
	}

	public LinkedList<TestCaseClass> createTestSuite() {
		setTestCaseSheetName("TestCase");
		LinkedList<TestCaseClass> testSuite = new LinkedList<TestCaseClass>();
		Xls_Reader objXLSReader = new Xls_Reader(new ReadPropertiesFile().getValue("ExcelPath"));
		setColCount(objXLSReader.getColumnCount(getTestCaseSheetName()));
		setRowCount(objXLSReader.getRowCount(getTestCaseSheetName()));
		for (int col = 5; col <= getColCount(); col++) {
			testSuite.add(createTestCase(col, objXLSReader));
		}
		return testSuite;
	}

	public TestCaseClass createTestCase(int col, Xls_Reader objXLSReader) {
		TestCaseClass testCase = new TestCaseClass();
		testCase.setTestCaseName(objXLSReader.getCellData(getTestCaseSheetName(), col-1, 1));
		for (int row = 3; row <= getRowCount() &&
				"nofill".equalsIgnoreCase(objXLSReader.getCellBackgroudColour(getTestCaseSheetName(), row, col)); row++) {
			testCase.addTestStep(createTestStep(row, col, objXLSReader));
		}
		return testCase;
	}

	public TestStepClass createTestStep(int row, int col, Xls_Reader objXLSReader) {
		TestStepClass objTestStep = new TestStepClass();
		String locatorName = objXLSReader.getCellData(getTestCaseSheetName(), 2, row);
		int locatorValueLocation = objXLSReader.getCellRowNum("Locator", "LocatorName", locatorName);
		setLocator(objXLSReader.getCellData("Locator", 2, locatorValueLocation));
		setKeyword(objXLSReader.getCellData(testCaseSheetName, 1, row));
		setColour(objXLSReader.getCellBackgroudColour(getTestCaseSheetName(), row, col));
		setData(objXLSReader.getCellData(testCaseSheetName, col - 1, row));
		objTestStep.setTupleValues(getKeyword(), getLocator(), getData(), getColour());
		// testCase.add(objTestStep);
		return objTestStep;
	}

}
