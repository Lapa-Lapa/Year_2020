package com.epam.atm.yandex.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverCreator implements DriverCreator {

	/**
	 * {@inheritDoc}
	 */
	@Override public WebDriver createWebDriver() {
		io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
		return new CustomDriverDecorator(new FirefoxDriver());
	}
}
