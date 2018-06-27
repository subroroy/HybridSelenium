package util.readexcel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class StudyTuples {

	public static void main(String[] args) {
		TestStepTuple<Integer, String, String, String> t = new TestStepTuple<Integer, String, String, String>(1, "foo", "foo2", "foo3");
		//TestCaseTuple<Integer, TestStepTuple<Integer, String, String, String>> t1 = new TestCaseTuple<Integer, TestStepTuple<Integer, String, String, String>>(2, t);
	

		System.out.println(t);
		//System.out.println(t1);
	}
	
	public ArrayList<LinkedHashMap<String, TestStepTuple<Integer, String, String, String>>> createTestCaseList(){
		List<LinkedHashMap<String, TestStepTuple<Integer, String, String, String>>> list = new ArrayList<LinkedHashMap<String, TestStepTuple<Integer, String, String, String>>>();
		return (ArrayList<LinkedHashMap<String, TestStepTuple<Integer, String, String, String>>>)list;
	}
	
	public void addStepToTestCase(String stepName, ArrayList<LinkedHashMap<String, TestStepTuple<Integer, String, String, String>>> list, TestStepTuple<Integer, String, String, String> testStep){
		LinkedHashMap<String, TestStepTuple<Integer, String, String, String>> test = new LinkedHashMap<String, TestStepTuple<Integer, String, String, String>>();
        test.put(stepName,testStep);
        list.add(test);
	}

}

class TestStepTuple<K, V1, V2, V3> 
{
	private final K key;
	private final V1 Control;
	private final V2 Keyword;
	private final V3 Data;

	public TestStepTuple(K key, V1 Control, V2 Keyword, V3 Data) {
		this.key = key;
		this.Control = Control;
		this.Keyword=Keyword;
		this.Data=Data;
	}

	public String toString() {
		return String.format("KEY: '%s', CONTROL: '%s', KEYWORD: '%s', DATA: '%s'", key, Control, Keyword, Data);
	}
	
	public TestStepTuple<Integer, String, String, String> setTestStepTuple(Integer stepNo, String control, String keyword, String data){
		TestStepTuple<Integer, String, String, String> t = new TestStepTuple<Integer, String, String, String>(stepNo, control, keyword, data);
		return t;
	}
	
	public String getTestStepTupleValue(TestStepTuple<Integer, String, String, String> testStepObj, String variableName){
		String variableValue;
		
		switch(variableName){
			
			case "Control":
				variableValue = testStepObj.Control;
				break;
				
			case "Keyword":
				variableValue = testStepObj.Keyword;
				break;
				
			case "Data":
				variableValue = testStepObj.Data;
				break;
				
			case "Key":
				variableValue = (testStepObj.key).toString();
				
			default:
				variableValue = "";
		}
		
		return variableValue;
	}


}
/*
class TestCaseTuple<K, V> 
{
	private final K k;
	private final V v;

	public TestCaseTuple(K key, V value) {
		k = key;
		v = value;
	}

	public String toString() {
		return String.format("KEY: '%s', VALUE: '%s'", k, v);
	}

	public TestCaseTuple<Integer, > insertTestCaseTuple(String key, TestStepTuple obj, Integer stepNo){
		
		TestCaseTuple<Integer, TestStepTuple<Integer, String, String, String>> t1 = new TestCaseTuple<Integer, TestStepTuple<Integer, String, String, String>>(stepNo, obj);
	}
}
*/