package webdriver;

import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import reporting.Logger;
import exceptions.WebDriverInstantiationException;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {

	private static WebDriver instance;
	private static final int DEFAULT_TIMEOUT = 15;

	private WebDriverManager() {
	}

	public static WebDriver getWebDriver() {
		if (instance != null) {
			return instance;
		}
		instance = initWebDriver();
		return instance;
	}

	/**
	 * Static Factory Method https://cucumber.io/docs/guides/browser-automation
	 */
	private static WebDriver initWebDriver() {
		String browserName = System.getProperty("browser");
		WebDriver driver;
		switch (WebDriverTypes.get(browserName)) {
		case FIREFOX:
			io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			break;
		default:
			throw new WebDriverInstantiationException("ERROR! [" + browserName + "] - is invalid or unsupported name for browser.");
		}
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
