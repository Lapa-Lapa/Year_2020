package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Waiter;
import webdriver.WebDriverManager;

public interface BasePage {

	String getURL();

	default WebDriver driver() {
		return WebDriverManager.getWebDriver();
	}

	default void initElements() {
		PageFactory.initElements(driver(), this);
	}

	default void waitForPageIsLoaded() {
		JavascriptExecutor webDriverInstanceChrome = (JavascriptExecutor) driver();
		new Waiter().start("Wait for Page is loaded ", () -> webDriverInstanceChrome.executeScript("return document.readyState").toString().equals("complete"));
	}
}
