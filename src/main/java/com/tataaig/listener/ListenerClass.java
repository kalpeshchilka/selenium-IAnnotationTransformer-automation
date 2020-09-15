package com.tataaig.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.tataaig.reports.ExtentManager;
import com.tataaig.reports.ExtentReport;
import com.tataaig.reports.LogStatus;
import com.tataaig.utilities.TestUtils;

/**
 * 
 * Listener class which is implementing ITestListener and hence we can use this
 * to dynamically create reports, write logs.
 * 
 * @author kalpesh
 * 
 */
public class ListenerClass implements ITestListener {

	private static String TestcaseName;
	private static String TestcaseDescription;

	public static String getTestcaseName() {
		return TestcaseName;
	}

	public static void setTestcaseName(String testcaseName) {
		TestcaseName = testcaseName;
	}

	public void onTestStart(ITestResult result) {
		TestcaseName = result.getMethod().getMethodName();
		TestcaseDescription = result.getMethod().getDescription();
		setTestcaseName(TestcaseName);
		ExtentManager.setExtentTest(ExtentReport.report.startTest(TestcaseDescription));
	}

	public void onTestSuccess(ITestResult result) {
		LogStatus.pass(result.getMethod().getMethodName() + " test case is passed");
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onTestFailure(ITestResult result) {
		LogStatus.fail(result.getMethod().getDescription() + " is failed");
		LogStatus.fail(result.getThrowable().toString());
		LogStatus.fail("Failed", TestUtils.pullScreenshotPath());
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onTestSkipped(ITestResult result) {
		LogStatus.skip(result.getMethod().getDescription() + " is skipped");
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

}
