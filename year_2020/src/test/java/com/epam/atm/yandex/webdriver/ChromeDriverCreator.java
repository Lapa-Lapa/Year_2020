package com.epam.atm.yandex.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverCreator implements DriverCreator {

	/**
	 * {@inheritDoc}
	 */
	@Override public WebDriver createWebDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
		return new CustomDriverDecorator(new ChromeDriver(options));
	}
}
