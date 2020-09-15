package com.tataaig.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tataaig.browser.DriverManager;
import com.tataaig.utilities.SeleniumUtils.ELEMENT_LOCATE_BY;

/**
 * 
 * This class consist of all Action Methods used to perform on Web Page
 * 
 * @author kalpesh
 *
 */
public class BasePage {

	protected BasePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	public void waitForCertainPeriod(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public WebElement findElement(String locator, ELEMENT_LOCATE_BY ele) {
		WebElement element;
		switch (ele) {
		case XPATH:
			element = DriverManager.getDriver().findElement(By.xpath(locator));
			highlight(element);
			return element;

		case ID:
			element = DriverManager.getDriver().findElement(By.id(locator));
			highlight(element);
			return element;

		case NAME:
			element = DriverManager.getDriver().findElement(By.name(locator));
			highlight(element);
			return element;

		case LINKTEXT:
			element = DriverManager.getDriver().findElement(By.linkText(locator));
			highlight(element);
			return element;

		case CLASSNAME:
			element = DriverManager.getDriver().findElement(By.className(locator));
			highlight(element);
			return element;

		default:
			return null;
		}
	}

	public void clickElement(String locator, ELEMENT_LOCATE_BY ele) {
		findElement(locator, ele).click();
		waitForCertainPeriod(1000);
	}

	public void clickElementUsingJS(String locator, ELEMENT_LOCATE_BY element) {
		WebElement webElement = findElement(locator, element);
		JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
		executor.executeScript("arguments[0].click();", webElement);
		waitForCertainPeriod(3000);
	}

	public WebElement waitForClickabilityOfElement(String locator, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getDriver(), timeOutPeriod);
		return webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

	public void waitForPageLoad() {
		(new WebDriverWait(DriverManager.getDriver(), 5)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				JavascriptExecutor js = (JavascriptExecutor) d;
				String readyState = js.executeScript("return document.readyState").toString();
				return (Boolean) readyState.equals("complete");
			}
		});
	}

	public void waitForElementPresent(String locator, ELEMENT_LOCATE_BY ele, int waitTime) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), waitTime);
		switch (ele) {
		case XPATH:
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			break;

		case ID:
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
			break;

		case NAME:
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
			break;

		case LINKTEXT:
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
			break;

		case CLASSNAME:
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
			break;

		}
	}

	public void inputValue(String locator, String value, ELEMENT_LOCATE_BY ele) {
		findElement(locator, ele).clear();
		clickElement(locator, ele);
		findElement(locator, ele).sendKeys(value);
		waitForCertainPeriod(500);
	}

	public String getText(String locator, ELEMENT_LOCATE_BY ele) {
		waitForCertainPeriod(1000);
		return findElement(locator, ele).getText();
	}

	public String getCurrentUrl() {
		waitForCertainPeriod(1000);
		return DriverManager.getDriver().getCurrentUrl();
	}

	public void switchToFrameById(String frameId) {
		DriverManager.getDriver().switchTo().frame(frameId);
	}

	public void switchToFrameByIndex(int index) {
		DriverManager.getDriver().switchTo().frame(index);
	}

	public void switchFrameToDefaultContent() {
		DriverManager.getDriver().switchTo().defaultContent();
	}

	public void launchUrl(String url) {
		DriverManager.getDriver().get(url);
		waitForPageLoad();
	}

	public void highlight(WebElement element) {
		try {
			if (true) {
				JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"border: 2px solid red;");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollToElement(String locator, ELEMENT_LOCATE_BY element) {
		WebElement webElement = findElement(locator, element);
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", webElement);
	}
}
