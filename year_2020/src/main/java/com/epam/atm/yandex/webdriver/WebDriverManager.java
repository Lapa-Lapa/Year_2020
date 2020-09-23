package com.epam.atm.yandex.webdriver;

import com.epam.atm.yandex.exceptions.WebDriverInstantiationException;
import com.epam.atm.yandex.reporting.Logger;
import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {

	private static WebDriver instance;
	private static final int DEFAULT_TIMEOUT = 15;

	private WebDriverManager() {
	}

	public static WebDriver getWebDriver() {
		if (instance == null) {
			instance = initWebDriver();
		}
		return instance;
	}

	/**
	 * Static Factory Method https://cucumber.io/docs/guides/browser-automation
	 */
	private static WebDriver initWebDriver() {
		String browserName = System.getProperty("browser");
		WebDriver driver;
		//DriverCreator creator;
		switch (WebDriverTypes.get(browserName)) {
		case FIREFOX:
			//creator = new FireFoxDriverCreator();
			driver = new FireFoxDriverCreator().createWebDriver();
			break;
		case CHROME:
			//creator = new ChromeDriverCreator();
			driver = new ChromeDriverCreator().createWebDriver();
			break;
		default:
			throw new WebDriverInstantiationException("ERROR! [" + browserName + "] - is invalid or unsupported name for browser.");
		}
		//driver = creator.createWebDriver();
		Logger.info("Browser: " + browserName + " - successfully started");
		driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//IMPLICIT
		driver.manage().timeouts().setScriptTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//IMPLICIT
		Logger.info("PageLoadTimeout was set to: " + DEFAULT_TIMEOUT + " seconds");
		return driver;
	}

	@After
	public static void killWebDriver() {
		if (instance != null) {
			try {
				instance.quit();
			} catch (Exception e) {
				Logger.error("Kill Web Driver error");
			} finally {
				instance = null;
			}
		}
	}
}
