package glue_visibility.utils;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = { "glue_visibility" },
		//https://habr.com/ru/post/332754 glue(5/6)
		tags = { "@run" })//https://testingneeds.wordpress.com/tag/cucumberoptions
public class Runner {

}
