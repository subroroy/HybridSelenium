package util.readexcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
	private final String filename = "values.properties";
	
	public String getValue(String key){
		Properties properties = new Properties();
		try {
		  properties.load(new FileInputStream(filename));
		  String value = properties.getProperty(key);
		  return value;
		} catch (IOException e) {
		  e.printStackTrace();
		  return "";
		}
		
	}
}