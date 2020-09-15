package com.tataaig.browser;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.tataaig.constants.Constants;
import com.tataaig.reports.LogStatus;
import com.tataaig.utilities.ReadPropertyFile;

/**
 * 
 * Driver class is used to start browsers based on the pom.xml file. User
 * gets the option to work on firefox and chrome browser. Private constructor to
 * avoid external initialization - SingletonPattern is achieved
 * 
 * @author kalpesh
 * 
 */
public class Driver {

	public WebDriver driver = null;
	public DesiredCapabilities capability;

	private Driver(String browserName) throws MalformedURLException {
		startBrowser(browserName);
		driver.manage().window().maximize();
		driver.get(ReadPropertyFile.get("url"));
		driver.manage().deleteAllCookies();
		DriverManager.setWebDriver(driver);
	}

	private void startBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVERPATH);
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", Constants.GECKODRIVERPATH);
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
				driver = new FirefoxDriver();
			}
		} catch (Exception e) {
			LogStatus.fail(e);
		}
	}

	public static void initialize(String browserName) {
		if (DriverManager.getDriver() == null)
			try {
				new Driver(browserName);
			} catch (Exception e) {

			}
	}

	public static void quit() {
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
		}
	}
}
