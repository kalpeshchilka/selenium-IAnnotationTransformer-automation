package com.tataaig.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.tataaig.browser.DriverManager;
import com.tataaig.constants.Constants;
import com.tataaig.listener.ListenerClass;

/**
 * 
 * All the utilities needed for the framework is placed in this class including
 * excel utilities, screenshot capture. Used method overloading concept in
 * getCellContent Method.
 * 
 * @author kalpesh
 *
 */
public class TestUtils {

	public static FileInputStream fs;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static List<String> testCases = new ArrayList<String>();
	public static List<String> runStatus = new ArrayList<String>();
	public static List<String> testDescription = new ArrayList<String>();

	/**
	 * 
	 * Reads the data from the excel sheet and store the values in respective lists
	 * which will be used in annotation transformer class
	 *
	 * @throws Exception
	 * @author kalpesh
	 */
	public static void getRunStatus() throws Exception {
		try {
			fs = new FileInputStream(ReadPropertyFile.get("TestDataLocation"));
			workbook = new XSSFWorkbook(fs);
			sheet = workbook.getSheet("RunManager");
			for (int i = 1; i <= getLastRowNum("RunManager"); i++) {
				testCases.add(getCellContent("RunManager", i, "TestCaseName"));
				testDescription.add(getCellContent("RunManager", i, "Test Case Description"));
				runStatus.add(getCellContent("RunManager", i, "RunFlag"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Takes rowName and sheetName as parameter return row number based of rowname
	 * 
	 * @param sheetName
	 * @param rowName
	 * @return
	 * @author kalpesh
	 */
	public static int getRowNumForRowName(String sheetName, String rowName) {
		int rownum = 0;
		sheet = workbook.getSheet(sheetName);
		for (int i = 1; i <= getLastRowNum(sheetName); i++) {
			if (rowName.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue())) {
				rownum = i;
				break;
			}
		}
		return rownum;
	}

	/**
	 * 
	 * Takes columnName and sheetName as parameter return column number based of
	 * columnheader
	 * 
	 * @param sheetName
	 * @param columnname
	 * @return
	 * @author kalpesh
	 */
	public static int getColumnNumForColumnName(String sheetName, String columnName) {
		int colnum = 0;
		sheet = workbook.getSheet(sheetName);
		for (int i = 0; i < getLastColumnNum(sheetName, 0); i++) {
			if (columnName.equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue())) {
				colnum = i;
				break;
			}
		}
		return colnum;
	}

	/**
	 * 
	 * Takes sheetName as parameter return last row number of the sheet
	 * 
	 * @param sheetName
	 * @return
	 * @author kalpesh
	 */
	public static int getLastRowNum(String sheetName) {
		return workbook.getSheet(sheetName).getLastRowNum();
	}

	/**
	 * 
	 * Takes sheetName, row number as parameter return last cell number of the row
	 *
	 * @param sheetName
	 * @param rowNum
	 * @return
	 * @author kalpesh
	 */
	public static int getLastColumnNum(String sheetName, int rowNum) {
		return workbook.getSheet(sheetName).getRow(rowNum).getLastCellNum();
	}

	/**
	 * Takes sheetName, row number, column number as parameter return cell value
	 *
	 * @param sheetName
	 * @param rowNum
	 * @param colNum
	 * @return
	 * @author kalpesh
	 */
	@SuppressWarnings("incomplete-switch")
	public static String getCellContent(String sheetName, int rowNum, int colNum) {
		sheet = workbook.getSheet(sheetName);
		Cell cell = sheet.getRow(rowNum).getCell(colNum);
		String cellValue = "";
		if (!(cell == null || cell.getCellType() == CellType.BLANK)) {
			
			switch (cell.getCellType()) {
			case STRING:
				cellValue = cell.getStringCellValue().concat("").toString();
				break;

			case NUMERIC:
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			}
		}
		return cellValue;
	}

	/**
	 * Takes sheetName, row number, column name as parameter return cell value
	 *
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @param columnName
	 * @return
	 * @author kalpesh
	 */
	public static String getCellContent(String sheetName, int rowNum, String columnName) {
		sheet = workbook.getSheet(sheetName);
		return sheet.getRow(rowNum).getCell(getColumnNumForColumnName(sheetName, columnName)).getStringCellValue()
				.concat("").toString();
	}

	/**
	 * 
	 * Takes screenshot Make sure parameter ScreenshotsRequired is Yes in
	 * TestRunDetails.properties
	 * 
	 * @author kalpesh
	 */
	public static void takeScreenshot() {
		if (ReadPropertyFile.get("ScreenshotsRequired").equalsIgnoreCase("yes")) {
			File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File("./screenshots/" + ListenerClass.getTestcaseName() + "/"
						+ System.currentTimeMillis() + new Random().nextInt(20) + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Captures screenshot and returns the screenshot path
	 *
	 * 
	 * @return
	 * @author kalpesh
	 */
	public static String pullScreenshotPath() {
		String destination = null;
		if (ReadPropertyFile.get("ScreenshotsRequired").equalsIgnoreCase("yes")) {
			File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			try {
				destination = System.getProperty("user.dir") + "//screenshots//" + ListenerClass.getTestcaseName()
						+ "//" + System.currentTimeMillis() + new Random().nextInt(20) + ".png";
				FileUtils.copyFile(scrFile, new File(destination));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return destination;
	}

	/**
	 * 
	 * Gives a base64 image which is used to append the screenshots in the extent
	 * report. Converting to base64 format avoids screenshots broken image if sent
	 * the extent report through email.
	 * 
	 * @param screenshotpath
	 * @return
	 * @author kalpesh
	 */
	public static String getBase64Image(String screenshotpath) {
		String base64 = null;
		try {
			InputStream is = new FileInputStream(screenshotpath);
			byte[] imageBytes = IOUtils.toByteArray(is);
			base64 = Base64.getEncoder().encodeToString(imageBytes);
		} catch (Exception e) {

		}
		return base64;
	}

	/**
	 * DataProvider method used to provide data for multiple iterations. Never try
	 * to use multiple iterations when the invocation count is greater than 1. It
	 * may result in adhoc results. As long as the first name of the TestData has
	 * the same test case name it will be treated as iteration.
	 * 
	 * @param m
	 * @return
	 * @author kalpesh
	 */
	@DataProvider(name = "dataProviderForIterations", parallel = true)
	public static Object[][] supplyDataForIterations(Method m) {
		System.out.println("inside dataProviderForIterations");
		return getDataForDataprovider(ReadPropertyFile.get("TestDataLocation"), Constants.TESTDATASHEETNAME,
				m.getName());
	}

	/**
	 * Finding number of iterations available for test case and return the data
	 * accordingly. Using hashtable avoids multiple parameters entry to the test
	 * case.
	 * 
	 * @param testData
	 * @param sheetName
	 * @param testCaseName
	 * @return
	 * @author kalpesh
	 */
	private static Object[][] getDataForDataprovider(String testData, String sheetName, String testCaseName) {
		int totalcolumns = getLastColumnNum(sheetName, 0);
		ArrayList<Integer> rowscount = getNumberofIterationsForATestCase(sheetName, testCaseName);
		Object[][] b = new Object[rowscount.size()][1];
		Hashtable<String, String> table = null;
		for (int i = 1; i <= rowscount.size(); i++) {
			table = new Hashtable<String, String>();
			for (int j = 0; j < totalcolumns; j++) {
				table.put(getCellContent(sheetName, 0, j), getCellContent(sheetName, rowscount.get(i - 1), j));
				b[i - 1][0] = table;
			}
		}
//		for (int i = 0; i < b.length; i++) {
//			System.out.println(Arrays.toString(b[i]));
//
//		}
		return b;
	}

	/**
	 * Used to return the rownumber of the test cases for multiple iterations.
	 * Suppose if testcase 1 is available in row 4 and 7 is test data , it return
	 * the arraylist with values 4,7
	 * 
	 * @param sheetName
	 * @param testCaseName
	 * @return
	 * @author kalpesh
	 */
	private static ArrayList<Integer> getNumberofIterationsForATestCase(String sheetName, String testCaseName) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 1; i <= getLastRowNum(sheetName); i++) {
			if (testCaseName.equalsIgnoreCase(getCellContent(sheetName, i, 0))) {
				a.add(i);
			}
		}
		return a;
	}

}
