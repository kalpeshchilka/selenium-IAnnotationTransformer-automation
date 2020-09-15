package com.tataaig.pages;

import com.tataaig.utilities.SeleniumUtils.ELEMENT_LOCATE_BY;

/**
 * 
 * This class consist of all the locators need to perform actions on Health
 * Insurance Page
 * 
 * @author kalpesh
 *
 */
public class HealthInsurancePage extends BasePage {

	public static String birthDateField = "//input[@placeholder='DD/MM/YYYY']";
	public static String mobileNumberField = "//input[@placeholder='Enter Your Mobile Number']";
	public static String seePlanBtn = "//button[text()='See Plan']";
	public static String buyNowBtn = "//button[@type='submit' and text()='Buy Now At']";
	public static String iAmGoodToGoBtn = "//button[@type='submit' and text()='I am Good to Go']";
	public static String nameField = "//input[@type='text' and @name='name']";
	public static String emailField = "//input[@type='email' and @name='emailId']";
	public static String tncCheckbox = "//label[@for='tnc']/span[contains(text(), 'I Agree to the')]";
	public static String payBtn = "//button[@type='submit' and text()='Pay']";

	public void enterBirthDate(String memberBirthDateValue) {
		waitForElementPresent(birthDateField, ELEMENT_LOCATE_BY.XPATH, 200);
		inputValue(birthDateField, memberBirthDateValue, ELEMENT_LOCATE_BY.XPATH);
	}

	public void enterMobileNumber(String memberMobNumberValue) {
		waitForElementPresent(mobileNumberField, ELEMENT_LOCATE_BY.XPATH, 200);
		inputValue(mobileNumberField, memberMobNumberValue, ELEMENT_LOCATE_BY.XPATH);
	}

	public void clickSeePlanBtn() {
		waitForElementPresent(seePlanBtn, ELEMENT_LOCATE_BY.XPATH, 200);
		clickElement(seePlanBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(1000);
	}

	public void clickBuyNow() {
		waitForElementPresent(buyNowBtn, ELEMENT_LOCATE_BY.XPATH, 200);
		clickElement(buyNowBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(500);
	}

	public void clickIAmGoodToGoBtn() {
		waitForElementPresent(iAmGoodToGoBtn, ELEMENT_LOCATE_BY.XPATH, 200);
		clickElement(iAmGoodToGoBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(500);
	}

	public void enterPolicyMemberDetails(String memberNameValue, String memberEmailIdValue) {
		waitForElementPresent(nameField, ELEMENT_LOCATE_BY.XPATH, 200);
		inputValue(nameField, memberNameValue, ELEMENT_LOCATE_BY.XPATH);
		waitForElementPresent(emailField, ELEMENT_LOCATE_BY.XPATH, 2000);
		inputValue(emailField, memberEmailIdValue, ELEMENT_LOCATE_BY.XPATH);
	}

	public void checkTncAndPay() {
		waitForElementPresent(tncCheckbox, ELEMENT_LOCATE_BY.XPATH, 200);
		clickElement(tncCheckbox, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(500);
		clickElement(payBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(500);
	}
}
