package com.epam.atm.yandex.webdriver;

import org.openqa.selenium.WebDriver;

public interface DriverCreator {

	/**
	 * Factory method pattern implementation
	 * @return new WebDriver instance
	 */
	WebDriver createWebDriver();
}
