package com.tataaig.pages;

import org.testng.Assert;

import com.tataaig.utilities.SeleniumUtils.ELEMENT_LOCATE_BY;

/**
 * 
 * This class consist of all the locators need to perform actions on Payment
 * Page
 * 
 * @author kalpesh
 *
 */
public class PaymentPage extends BasePage {

	public static String cardPaymentBtn = "*//div/button[@method='card']";
	public static String cardNumberField = "//input[@name='card[number]']";
	public static String cvvField = "//input[@name='card[cvv]']";
	public static String expiryDateField = "//input[@name='card[expiry]']";
	public static String payBtn = "//div[@class='button']/span[contains(text(), 'Pay')]";
	public static String paymentStatus = "//div[contains(@class,'paymentStatus')]";

	public void clickCardPaymentBtn() {
		waitForCertainPeriod(500);
		switchToFrameByIndex(0);
		waitForElementPresent(cardPaymentBtn, ELEMENT_LOCATE_BY.XPATH, 1000);
		clickElement(cardPaymentBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(500);
	}

	public void enterCreditCardDetails(String cardNumberValue, String expiryDateValue, String cvvValue) {
		waitForElementPresent(cardNumberField, ELEMENT_LOCATE_BY.XPATH, 1000);
		inputValue(cardNumberField, cardNumberValue, ELEMENT_LOCATE_BY.XPATH);
		inputValue(expiryDateField, expiryDateValue, ELEMENT_LOCATE_BY.XPATH);
		inputValue(cvvField, cvvValue, ELEMENT_LOCATE_BY.XPATH);
	}

	public void clickPayBtn() {
		clickElement(payBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(500);
		switchFrameToDefaultContent();
	}

	public void assertPaymentUnsuccessfulStatus() {
		waitForCertainPeriod(500);
		waitForElementPresent(paymentStatus, ELEMENT_LOCATE_BY.XPATH, 1000);
		String paymentStatusMsg = getText(paymentStatus, ELEMENT_LOCATE_BY.XPATH);
		System.out.println("paymentStatusMsg: " + paymentStatusMsg);
		Assert.assertEquals(paymentStatusMsg, "Payment Unsuccessful incorret");
	}

}
