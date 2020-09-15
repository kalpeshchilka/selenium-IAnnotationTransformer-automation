package com.tataaig.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.tataaig.pages.HealthInsurancePage;
import com.tataaig.pages.HomePage;
import com.tataaig.pages.MediCarePlusPage;
import com.tataaig.pages.PaymentPage;

public class TataAIGTest extends BaseTest {

	HomePage homePage = new HomePage();
	HealthInsurancePage healthInsurancePage = new HealthInsurancePage();
	PaymentPage paymentPage = new PaymentPage();
	MediCarePlusPage mediCarePlusPage = new MediCarePlusPage();

	@Test()
	public void buyHealthPolicyForSelf(Hashtable<String, String> data) {
		homePage.clickHealthPolicy();
		homePage.clickInsureSelfAndGetPlan();
		healthInsurancePage.enterBirthDate(data.get("memberBirthDate"));
		healthInsurancePage.enterMobileNumber(data.get("memberMobNumber"));
		healthInsurancePage.clickSeePlanBtn();
		healthInsurancePage.clickBuyNow();
		healthInsurancePage.clickIAmGoodToGoBtn();
		healthInsurancePage.enterPolicyMemberDetails(data.get("memberName"), data.get("memberEmailId"));
		healthInsurancePage.checkTncAndPay();
		paymentPage.clickCardPaymentBtn();
		paymentPage.enterCreditCardDetails(data.get("cardNumber"), data.get("expiryDate"), data.get("cvv"));
		paymentPage.clickPayBtn();
		paymentPage.assertPaymentUnsuccessfulStatus();
	}

	@Test()
	public void contactCustomerRepAboutMediCarePlus(Hashtable<String, String> data) {
		homePage.clickBottomProductNavigation();
		homePage.clickMediCarePlusBtn();
		mediCarePlusPage.clickHaveUsCallYouBtn();
		mediCarePlusPage.enterCustomerDetails(data.get("memberName"), data.get("memberMobNumber"),
				data.get("memberEmailId"));
		mediCarePlusPage.clickProceedBtn();
		mediCarePlusPage.assertThankYouMessage();
	}

}
