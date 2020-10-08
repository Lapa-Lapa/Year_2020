package com.epam.atm.yandex.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.epam.atm.yandex.reporting.Logger;

import java.util.List;
import java.util.Set;

/**
 * This class is used to customize WebDriver and JavascriptExecutor methods
 */
public class CustomDriverDecorator implements WebDriver, JavascriptExecutor {

	private WebDriver driver;

	CustomDriverDecorator(WebDriver driver) {
		this.driver = driver;
	}

	@Override public void get(String s) {
		driver.get(s);
	}

	@Override public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	@Override public String getTitle() {
		return driver.getTitle();
	}

	@Override public <T extends WebElement> List<T> findElements(By by) {
		return driver.findElements(by);
	}

	@Override public <T extends WebElement> T findElement(By by) {
		return driver.findElement(by);
	}

	@Override public String getPageSource() {
		return driver.getPageSource();
	}

	@Override public void close() {
		driver.close();
	}

	@Override public void quit() {
		driver.quit();
	}

	@Override public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	@Override public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override public TargetLocator switchTo() {
		return driver.switchTo();
	}

	@Override public Navigation navigate() {
		return driver.navigate();
	}

	@Override public Options manage() {
		return driver.manage();
	}

	@Override public Object executeScript(String s, Object... objects) {
		Logger.info("Starting execution script: " + s);
		return ((JavascriptExecutor) driver).executeScript(s, objects);
	}

	@Override public Object executeAsyncScript(String s, Object... objects) {
		return ((JavascriptExecutor) driver).executeAsyncScript(s, objects);
	}
}
