package glue_visibility.tests_steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import reporting.Logger;
import webdriver.WebDriverManager;

public class BaseTestSteps {

	@Before
	public void setUp(Scenario scenario) {
		Logger.info(String.format("START TEST SCENARIO WITH NAME - %s", scenario.getName()));
		WebDriverManager.getWebDriver();
	}

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
