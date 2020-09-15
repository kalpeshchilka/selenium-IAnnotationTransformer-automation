package com.tataaig.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertyFile {
	/**
	 * 
	 * To get the data from TestRunDetails.properties file
	 * 
	 * @param propertyname String key for which value is needed from the
	 *                     TestRunDetails.properites file
	 * @return returns data from the TestRunDetails.properites file based based on the
	 *         propertyname provided
	 * @author kalpesh
	 */
	public static String get(String propertyname) {
		String returnProperty = null;
		Properties property = new Properties();
		try {
			FileInputStream file = new FileInputStream(
					new File(System.getProperty("user.dir") + "//resources//TestRunDetails.properties"));
			property.load(file);
			returnProperty = property.getProperty(propertyname);
			if (returnProperty == null) {
				throw new Exception("Property named " + propertyname + "not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnProperty;
	}

}
