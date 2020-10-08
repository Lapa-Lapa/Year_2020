package com.epam.atm.yandex.steps;

import com.epam.atm.yandex.bo.Account;
import com.epam.atm.yandex.bo.Letter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import com.epam.atm.yandex.pages.HomePage;
import com.epam.atm.yandex.utils.RandomString;
import com.epam.atm.yandex.exceptions.TestDataException;

@ContextConfiguration(locations = { "classpath:test_data/user_data.xml" })
public class HomePageSteps extends AbstractJUnit4SpringContextTests {

	private final HomePage homePage = new HomePage();

	@Given("^Open home page$")
	public void openHomePage() {
		homePage.openHomePage();
	}

	@And("^Click 'Enter' button$")
	public void enterButtonClick() {
		homePage.enterButtonClick();
	}

	@And("^Login with \"([^\"]+)\"$")
	public void fillInEmail(String user) {
		try {
			Account account = (Account) applicationContext.getBean(user);
			homePage.fillInEmail(account.getEmail());
			homePage.submitButtonClick();
			homePage.fillInPassword(account.getPassword());
		} catch (NoSuchBeanDefinitionException e) {
			throw new TestDataException("Please check if :\"" + user + "\" - bean is defined in user_data.xml");
		}
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

	@And("^Type text in \"([^\"]+)\" field$")
	public void typeTextInAddresseeField(String field) {
		homePage.typeTextInField(RandomString.getRandomString(), field);
	}

	@And("^Send email to: \"([^\"]+)\"$")
	public void fillInAllFields(String addressee) {
		homePage.typeTextInField(Letter.createLetterWithAddressee(addressee));
		homePage.sendButtonButtonClick();
	}
}
