package fr.eni.encheres.bll.error;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ErrorManager {

	private Properties errorsProperties;
	
	public ErrorManager() {
		loadErrorsProperties();
	}

	private void loadErrorsProperties() {

		errorsProperties = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("fr/eni/encheres/bll/error/errors.properties")) 
		{
			
			if (input != null) {
				errorsProperties.load(input);
			} else {
				System.err.println("File errors.properties unreachable.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getErrorMessage(String errorCode) {
		String errorMessage = errorsProperties.getProperty(errorCode);
		return (errorMessage != null) ? errorMessage : "Unknown Error Code : " + errorCode;
	}
	
}
