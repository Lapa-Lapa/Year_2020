package com.epam.atm.yandex.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = { "com/epam/atm/yandex/steps" },
		//https://habr.com/ru/post/332754/
		tags = { "@run" })//https://testingneeds.wordpress.com/tag/cucumberoptions
public class Runner {

}
