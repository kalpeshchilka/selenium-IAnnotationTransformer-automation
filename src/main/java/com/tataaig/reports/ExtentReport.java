package com.tataaig.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.tataaig.constants.Constants;
import com.tataaig.utilities.ReadPropertyFile;

public class ExtentReport {
	public static ExtentReports report = null;
	public static String extentreportpath = "";

	// To avoid external initialization
	private ExtentReport() {
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy_ hh_mm_ss");
		Date date = new Date();
		String currentDate = formatter.format(date);
		if (ReadPropertyFile.get("OverrideResults").equalsIgnoreCase("yes")) {
			extentreportpath = Constants.EXTENTREPORTPATH + "//TestReport.html";
		} else {
			extentreportpath = Constants.EXTENTREPORTPATH + "//TestReport_" + currentDate + ".html";
		}
		report = new ExtentReports(extentreportpath);
		report.loadConfig(new File(Constants.EXTENTCONFIGPATH));
	}

	@SuppressWarnings("unused")
	public static void initialize() {
		ExtentReport report = new ExtentReport();
	}

}
