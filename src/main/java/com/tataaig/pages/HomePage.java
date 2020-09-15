package com.tataaig.pages;

import org.testng.Assert;

import com.tataaig.utilities.SeleniumUtils.ELEMENT_LOCATE_BY;

/**
 * 
 * This class consist of all the locators need to perform actions on Home Page
 * 
 * @author kalpesh
 *
 */
public class HomePage extends BasePage {
	
	public static String healthPolicyBtn = "//span[contains(text(),'health')]";
	public static String insureSelfBtn = "//button/img[@src='/health/Self.svg']";
	public static String getPlanBtn = "//button[@type='submit' and contains(text(),'Get Plan')]";
	public static String bottomProductNav = "(//span[@class=\"accordionShadow\"])[1]";
	public static String mediCarePlusBtn = "//a[text()= 'MediCare Plus']";

	public void clickHealthPolicy() {
		clickElement(healthPolicyBtn, ELEMENT_LOCATE_BY.XPATH);
	}

	public void clickInsureSelfAndGetPlan() {
		waitForElementPresent(insureSelfBtn, ELEMENT_LOCATE_BY.XPATH, 200);
		clickElement(insureSelfBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(100);
		clickElement(getPlanBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(100);
		Assert.assertTrue(getCurrentUrl().contains("health-insurance"));
	}

	public void clickBottomProductNavigation() {
		scrollToElement(bottomProductNav, ELEMENT_LOCATE_BY.XPATH);
		waitForElementPresent(bottomProductNav, ELEMENT_LOCATE_BY.XPATH, 200);
		clickElement(bottomProductNav, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(100);
	}

	public void clickMediCarePlusBtn() {
		scrollToElement(mediCarePlusBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForElementPresent(mediCarePlusBtn, ELEMENT_LOCATE_BY.XPATH, 200);
		clickElement(mediCarePlusBtn, ELEMENT_LOCATE_BY.XPATH);
		waitForCertainPeriod(100);
		Assert.assertTrue(getCurrentUrl().contains("medicare-plus"));
	}
}
