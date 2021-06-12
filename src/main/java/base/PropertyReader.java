package base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	public String getPropertyValue(String propName) {
		String propValue = "";
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("Application.properties")){
			
			Properties prop = new Properties();
			
			if(input == null) {
				System.out.println("Sorry, unable to find Application.properties");
			}
			
			// load a properties file from class path, inside static method
			prop.load(input);
			propValue = prop.getProperty(propName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propValue;
	}
}
