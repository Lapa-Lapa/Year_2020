package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.Waiter;
import webdriver.WaitersAndUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static webdriver.WaitersAndUtils.*;

public class HomePage extends BasePage {

	private static final Logger LOGGER = Logger.getLogger(HomePage.class);
	private static final String URL = "https://mail.yandex.ru/";
	private static final String NEW_LETTER_POP_UP = "//*[contains(@class, 'ComposePopup_size_medium') and not (contains(@class,'ComposeManager-PopupCompose_hidden'))]";
	public static final String HEADER = "//div[contains(@class,'HeadBanner-Inner')]";
	public static final String PASSWORD_INPUT = "//input[contains(@data-t, 'passwd')]";
	@FindBy(how = How.XPATH, using = HEADER + "//a[contains(@href,'auth')]")
	public static WebElement enterButton;
	@FindBy(how = How.XPATH, using = "//input[contains(@name, 'login')]")
	public static WebElement loginInput;
	@FindBy(how = How.XPATH, using = PASSWORD_INPUT)
	public static WebElement passwordInput;
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Кому')]/parent::label)/following-sibling::div/div/div")
	public static WebElement addresseeInput;
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'SuggestItemDesktop-Name')])[1]")
	public static WebElement firstSuggestedItem;
	@FindBy(how = How.XPATH, using = "((//*[contains(text(), 'Тема')]/parent::label)/following-sibling::div)[1]")
	public static WebElement subjectInput;
	@FindBy(how = How.XPATH, using = "((//*[contains(text(), 'Тема')]/parent::label)/following-sibling::div)[1]/input")
	public static WebElement subjectFocusedInput;
	@FindBy(how = How.XPATH, using = "//div[contains(@placeholder, 'Напишите что-нибудь')]")
	public static WebElement letterTextInput;
	@FindBy(how = How.XPATH, using = "//*[contains(@type, 'submit')]")
	public WebElement submitButton;
	public static final String HEADER_LOGO = "//div[contains(@class, 'header__logo')]";
	@FindBy(how = How.XPATH, using = HEADER_LOGO)
	public WebElement headerLogo;
	public static final String NEW_EMAIL_BUTTON = "//*[contains(text(), 'Написать')]";
	@FindBy(how = How.XPATH, using = NEW_EMAIL_BUTTON)
	public WebElement newEmailButton;
	@FindBy(how = How.XPATH, using = NEW_LETTER_POP_UP + "//*[contains(text(), 'Отправить')]/parent::*/parent::*/parent::*")
	public WebElement sendButton;
	@FindBy(how = How.XPATH, using = "//span[contains(text(), 'Письмо отправлено')]")
	public WebElement letterWasSendText;

	@Override
	public String getURL() {
		return URL;
	}

	public HomePage() {
		initElements();
	}

	public void openHomePage() {
		driver().navigate().to(URL);
		LOGGER.info(URL + " is open");
		BasePage.waitForPageIsLoaded();
	}

	public void enterButtonClick() {
		enterButton.click();
		LOGGER.info("'Enter' button click");
	}

	public void fillInEmail(String email) {
		CharSequence charSequence = email;
		new Waiter().start("Waiting for Email field appear.", () -> loginInput.isDisplayed());
		loginInput.sendKeys(charSequence);
	}

	public void fillInPassword(String password) {
		CharSequence charSequence = password;
		WaitersAndUtils.waitForElementPresent(By.xpath(PASSWORD_INPUT), driver());
		passwordInput.sendKeys(charSequence);
	}

	public void submitButtonClick() {
		new Waiter().start("Waiting for 'Submit' button appear.", () -> submitButton.isDisplayed());
		submitButton.click();
		LOGGER.info("'Submit' button click");
	}

	public boolean isHeaderLogoPresent() {
		waitForElementPresent(By.xpath(HEADER_LOGO), driver());
		return headerLogo.isDisplayed();
	}

	public void newEmailButtonButtonClick() {
		waitForElementPresent(By.xpath(NEW_EMAIL_BUTTON), driver());
		newEmailButton.click();
		LOGGER.info("'Write' button click");
		waitForPageIsLoaded();
	}

	public void sendButtonButtonClick() {
		waitForElementClickable(sendButton);
		sendButton.click();
		LOGGER.info("'Send' button click");
		waitForPageIsLoaded();
	}

	public boolean isLetterWasSendTextPresent() {
		waitForElementVisible(letterWasSendText);
		return letterWasSendText.isDisplayed();
	}

	public void typeTextInField(String text, String field) {
		CharSequence[] charSequence = { text };
		switch (field) {
		case "ADDRESSEE":
			waitForElementVisible(addresseeInput);
			addresseeInput.click();
			addresseeInput.sendKeys(charSequence);
			waitForElementClickable(firstSuggestedItem);
			firstSuggestedItem.click();
			break;
		case "SUBJECT":
			waitForElementVisible(subjectFocusedInput);
			subjectFocusedInput.click();
			subjectFocusedInput.sendKeys(charSequence);
			break;
		case "LETTER_TEXT":
			waitForElementVisible(letterTextInput);
			letterTextInput.click();
			letterTextInput.sendKeys(charSequence);
			break;
		}
		LOGGER.info(text + " - was typed in '" + field + "' field");
	}
}
