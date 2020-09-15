package com.tataaig.testcases;

import java.awt.Desktop;
import java.io.File;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.tataaig.browser.Driver;
import com.tataaig.browser.DriverManager;
import com.tataaig.reports.ExtentReport;

public class BaseTest {

	@Parameters("browserName")
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browserName) {
//		System.out.println("inside @BeforeMethod");
		try {
			Driver.initialize(browserName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void wrapUp() {
		DriverManager.getDriver().close();
	}

	@BeforeSuite
	public void beforeSuite() throws Exception {
//		System.out.println("inside @@BeforeSuite");
		ExtentReport.initialize();
	}

	@AfterSuite
	public void afterSuite() throws Exception {
		ExtentReport.report.flush();
		File htmlFile = new File(ExtentReport.extentreportpath);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}
}
