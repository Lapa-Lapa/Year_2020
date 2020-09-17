package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static webdriver.WebDriverManager.getWebDriver;

public class WaitersAndUtils {

	private WaitersAndUtils() {
	}

	private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 35;

	/**
	 * ЯВНЫЕ (EXPLICIT) ОЖИДАНИЯ Explicit Waits: https://seleniumjava.com/2016/04/05/the-beginners-guide-to-explicit-waits
	 */
	public static void waitForElementClickable(WebElement webElement) {
		new WebDriverWait(getWebDriver(), WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public static void waitForElementPresent(By locator, WebDriver driver) {
		new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitForAllElementsPresent(By locator, WebDriver driver) {
		new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public static void waitForElementVisible(By locator, WebDriver driver) {
		new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitForElementsVisible(By locator, WebDriver driver) {
		new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public static void waitForElementVisible(WebElement webElement) {
		new WebDriverWait(getWebDriver(), WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(webElement));
	}

	public static void checkUrlToBe(String url, WebDriver driver) {
		new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.urlToBe(url));
	}

	/**
	 * НЕЯВНЫЕ ИЛИ СКРЫТЫЕ(IMPLICIT) ОЖИДАНИЯ Implicit Waits: https://seleniumjava.com/2015/12/12/how-to-make-selenium-webdriver-scripts-faster
	 */
	public static void implicitWait(int timeoutInSec) {
		getWebDriver().manage().timeouts().implicitlyWait(timeoutInSec, TimeUnit.SECONDS);
	}
}
