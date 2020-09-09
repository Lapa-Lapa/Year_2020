package glue_visibility.tests_steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.HomePage;

public class HomePageSteps {

	private final HomePage homePage = new HomePage();

	@Given("^Open home page$")
	public void openHomePage() {
		homePage.openHomePage();
	}

	@And("^Click 'Enter' button$")
	public void enterButtonClick() {
		homePage.enterButtonClick();
	}

	@And("^Fill in email \"([^\"]+)\" and password \"([^\"]+)\"$")
	public void fillInEmail(String email, String password) {
		homePage.fillInEmail(email);
		homePage.submitButtonClick();
		homePage.fillInPassword(password);
	}

	@When("^'Submit' button click$")
	public void submitButtonClick() {
		homePage.submitButtonClick();
	}

	@Then("^Verify mail box is opened$")
	public void verifyMailBoxIsOpened() {
		Assert.assertTrue("Mail box is opened", homePage.isHeaderLogoPresent());
	}

	@Then("^'Write' button click$")
	public void newEmailButtonButtonClick() {
		homePage.newEmailButtonButtonClick();
	}

	@Then("^'Send' button click$")
	public void sendButtonButtonClick() {
		homePage.sendButtonButtonClick();
	}

	@Then("^Verify 'Letter was send' text is present$")
	public void verifyIsLetterWasSendTextPresent() {
		Assert.assertTrue(homePage.isLetterWasSendTextPresent());
	}

	@And("^Type \"([^\"]+)\" in \"([^\"]+)\" field$")
	public void typeTextInAddresseeField(String text, String field) {
		homePage.typeTextInField(text, field);
	}
}
