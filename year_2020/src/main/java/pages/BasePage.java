package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Waiter;
import webdriver.WebDriverManager;

public abstract class BasePage {

	private static final Logger LOGGER = Logger.getLogger(BasePage.class);

	public abstract String getURL();

	public static WebDriver driver() {
		return WebDriverManager.getWebDriver();
	}

	public void initElements() {
		PageFactory.initElements(driver(), this);
	}

	public static void waitForPageIsLoaded() {
		JavascriptExecutor webDriverInstanceChrome = (JavascriptExecutor) driver();
		new Waiter().start("Wait for Page is loaded ", () -> webDriverInstanceChrome.executeScript("return document.readyState").toString().equals("complete"));
	}
}
