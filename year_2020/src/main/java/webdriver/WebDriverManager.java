package webdriver;

import cucumber.api.java.After;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Exceptions.WebDriverInstantiationException;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {

	private static final Logger LOGGER = Logger.getLogger(WebDriverManager.class);
	private static WebDriver instance;
	private static final int DEFAULT_TIMEOUT = 15;

	private WebDriverManager() {
	}

	public static WebDriver getWebDriver() {
		if (instance != null) {
			return instance;
		}
		return instance = initWebDriver();
	}

	/**
	 * Static Factory Method https://cucumber.io/docs/guides/browser-automation
	 */
	private static WebDriver initWebDriver() {
		String browserName = System.getProperty("browser");
		WebDriver driver;
		switch (browserName) {
		case "firefox":
			io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			break;
		default:
			throw new WebDriverInstantiationException("ERROR! [" + browserName + "] - is invalid or unsupported name for browser.");
		}
		LOGGER.info("Browser: " + browserName + " - successfully started");
		driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//IMPLICIT
		driver.manage().timeouts().setScriptTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//IMPLICIT
		LOGGER.info("PageLoadTimeout was set to: " + DEFAULT_TIMEOUT + " seconds");
		return driver;
	}

	@After
	public static void killWebDriver() {
		if (instance != null) {
			try {
				instance.quit();
			} catch (Exception e) {
				LOGGER.error("Kill Web Driver error");
			} finally {
				instance = null;
			}
		}
	}
}
