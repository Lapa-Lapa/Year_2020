package com.epam.atm.yandex.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import com.epam.atm.yandex.reporting.Logger;
import com.epam.atm.yandex.webdriver.WebDriverManager;

public class BaseTestSteps {

	/**
	 * Before scenario method. It will always run no matter what steps class you use in scenario
	 */
	@Before
	public void setUp(Scenario scenario) {
		Logger.info(String.format("START TEST SCENARIO WITH NAME - %s", scenario.getName()));
		WebDriverManager.getWebDriver();
	}

	/**
	 * After scenario method. It will always run no matter what steps class you use after scenario
	 */
	@After
	public void tearDown(Scenario scenario) {
		if (!scenario.isFailed()) {
			Logger.info("SCENARIO PASSED -> OK!!!");
		} else {
			Logger.info("SCENARIO FAILED -> ERROR!!!");
			//TODO: Logging with Screenshots in case of fail ( week 13)
		}
		WebDriverManager.killWebDriver();
	}
}
