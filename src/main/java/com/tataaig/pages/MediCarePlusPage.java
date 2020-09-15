package com.tataaig.pages;

import org.testng.Assert;

import com.tataaig.utilities.SeleniumUtils.ELEMENT_LOCATE_BY;

public class MediCarePlusPage extends BasePage {

	public static String haveUsCallYouBtn = "(//button[contains(text(),'Have us Call You')])[1]";
	public static String nameField = "//input[@type='text' and @name='name']";
	public static String mobileNumberField = "//input[@type=\"text\" and @name=\"mobile\"]";
	public static String emailField = "//input[@type=\"text\" and @name=\"email\"]";
	public static String proceedBtn = "//button[text()='Proceed']";
	public static String thankYouMsg = "//button[contains(text(),'Thank You')]";

	public void clickHaveUsCallYouBtn() {
		scrollToElement(haveUsCallYouBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForElementPresent(haveUsCallYouBtn, ELEMENT_LOCATE_BY.XPATH, 200);
		clickElement(haveUsCallYouBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(100);
	}

	public void enterCustomerDetails(String memberNameValue, String mobileNumberValue, String memberEmailIdValue) {
		waitForElementPresent(nameField, ELEMENT_LOCATE_BY.XPATH, 200);
		inputValue(nameField, memberNameValue, ELEMENT_LOCATE_BY.XPATH);
		waitForElementPresent(mobileNumberField, ELEMENT_LOCATE_BY.XPATH, 200);
		inputValue(mobileNumberField, mobileNumberValue, ELEMENT_LOCATE_BY.XPATH);
		waitForElementPresent(emailField, ELEMENT_LOCATE_BY.XPATH, 2000);
		inputValue(emailField, memberEmailIdValue, ELEMENT_LOCATE_BY.XPATH);
	}

	public void clickProceedBtn() {
		clickElement(proceedBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(100);
	}

	public void assertThankYouMessage() {
		String thankYouMessage = getText(thankYouMsg, ELEMENT_LOCATE_BY.XPATH);
		System.out.println("thankYouMessage: " + thankYouMessage);
		Assert.assertEquals(thankYouMessage, "Thank You");
	}

}
