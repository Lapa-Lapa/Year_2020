package com.epam.atm.yandex.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.epam.atm.yandex.utils.Waiter;
import com.epam.atm.yandex.webdriver.WebDriverManager;

public interface BasePage {

	String getURL();

	default WebDriver driver() {
		return WebDriverManager.getWebDriver();
	}

	/**
	 * Init elements for page factory
	 */
	default void initElements() {
		PageFactory.initElements(driver(), this);
	}

	/**
	 * Method for waiting that page is loaded
	 */
	default void waitForPageIsLoaded() {
		JavascriptExecutor webDriverInstanceChrome = (JavascriptExecutor) driver();
		new Waiter().start("Wait for Page is loaded ", () -> webDriverInstanceChrome.executeScript("return document.readyState").toString().equals("complete"));
	}
}
