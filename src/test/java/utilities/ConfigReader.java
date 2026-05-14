package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	Properties prop;

	public ConfigReader() {

		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			prop.load(fis);
		} catch (IOException e) {

			e.printStackTrace(); // prints the error details

		}
	}

	public String getURL() {
		return prop.getProperty("url");

	}

	public String getUsername() {
		return prop.getProperty("Lusername");

	}

	public String getPassword() {
		return prop.getProperty("Lpassword");
	}

	public String getBrowser() {
		return prop.getProperty("browser");
	}

}