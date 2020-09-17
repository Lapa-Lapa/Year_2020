package pages;

import business_objects.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.Waiter;
import exceptions.TestDataException;
import utils.WaitersAndUtils;
import reporting.Logger;

import static utils.WaitersAndUtils.*;

public class HomePage implements BasePage {

	private static final String ADDRESSEE = "ADDRESSEE";
	private static final String SUBJECT = "SUBJECT";
	private static final String LETTER_TEXT = "LETTER_TEXT";
	private static final String URL = "https://mail.yandex.ru/";
	private static final String NEW_LETTER_POP_UP = "//*[contains(@class, 'ComposePopup_size_medium') and not (contains(@class,'ComposeManager-PopupCompose_hidden'))]";
	private static final String HEADER = "//div[contains(@class,'HeadBanner-Inner')]";
	private static final String PASSWORD_INPUT = "//input[contains(@data-t, 'passwd')]";
	@FindBy(how = How.XPATH, using = HEADER + "//a[contains(@href,'auth')]")
	private static WebElement enterButton;
	@FindBy(how = How.XPATH, using = "//input[contains(@name, 'login')]")
	private static WebElement loginInput;
	@FindBy(how = How.XPATH, using = PASSWORD_INPUT)
	private static WebElement passwordInput;
	@FindBy(how = How.XPATH, using = "(//*[contains(text(), 'Кому')]/parent::label)/following-sibling::div/div/div")
	private static WebElement addresseeInput;
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'SuggestItemDesktop-Name')])[1]")
	private static WebElement firstSuggestedItem;
	@FindBy(how = How.XPATH, using = "((//*[contains(text(), 'Тема')]/parent::label)/following-sibling::div)[1]/input")
	private static WebElement subjectFocusedInput;
	@FindBy(how = How.XPATH, using = "//div[contains(@placeholder, 'Напишите что-нибудь')]")
	private static WebElement letterTextInput;
	@FindBy(how = How.XPATH, using = "//*[contains(@type, 'submit')]")
	private WebElement submitButton;
	private static final String HEADER_LOGO = "//div[contains(@class, 'header__logo')]";
	@FindBy(how = How.XPATH, using = HEADER_LOGO)
	private WebElement headerLogo;
	private static final String NEW_EMAIL_BUTTON = "//*[contains(text(), 'Написать')]";
	@FindBy(how = How.XPATH, using = NEW_EMAIL_BUTTON)
	private WebElement newEmailButton;
	@FindBy(how = How.XPATH, using = NEW_LETTER_POP_UP + "//*[contains(text(), 'Отправить')]/parent::*/parent::*/parent::*")
	private WebElement sendButton;
	@FindBy(how = How.XPATH, using = "//span[contains(text(), 'Письмо отправлено')]")
	private WebElement letterWasSendText;

	@Override
	public String getURL() {
		return URL;
	}

	public HomePage() {
		initElements();
	}

	public void openHomePage() {
		driver().navigate().to(URL);
		Logger.info(URL + " - URL is open");
		waitForPageIsLoaded();
	}

	public void enterButtonClick() {
		enterButton.click();
		Logger.info("'Enter' button click");
	}

	public void fillInEmail(String email) {
		Logger.info("Fill in Email field: " + email);
		CharSequence charSequence = email;
		new Waiter().start("Waiting for Email field appear.", () -> loginInput.isDisplayed());
		loginInput.sendKeys(charSequence);
	}

	public void fillInPassword(String password) {
		Logger.info("Fill in Password field: " + password);
		CharSequence charSequence = password;
		WaitersAndUtils.waitForElementPresent(By.xpath(PASSWORD_INPUT), driver());
		passwordInput.sendKeys(charSequence);
	}

	public void submitButtonClick() {
		new Waiter().start("Waiting for 'Submit' button appear.", () -> submitButton.isDisplayed());
		submitButton.click();
		Logger.info("'Submit' button click");
	}

	public boolean isHeaderLogoPresent() {
		waitForElementPresent(By.xpath(HEADER_LOGO), driver());
		return headerLogo.isDisplayed();
	}

	public void newEmailButtonButtonClick() {
		waitForElementPresent(By.xpath(NEW_EMAIL_BUTTON), driver());
		newEmailButton.click();
		Logger.info("'Write' button click");
		waitForPageIsLoaded();
	}

	public void sendButtonButtonClick() {
		waitForElementClickable(sendButton);
		sendButton.click();
		Logger.info("'Send' button click");
		waitForPageIsLoaded();
	}

	public boolean isLetterWasSendTextPresent() {
		waitForElementVisible(letterWasSendText);
		return letterWasSendText.isDisplayed();
	}

	public void typeTextInField(Letter letter) {
		typeTextInField(letter.getAddressee(), ADDRESSEE);
		typeTextInField(letter.getSubject(), SUBJECT);
		typeTextInField(letter.getLetterText(), LETTER_TEXT);
	}

	public void typeTextInField(String text, String field) {
		CharSequence[] charSequence = { text };
		switch (field) {
		case ADDRESSEE:
			waitForElementVisible(addresseeInput);
			addresseeInput.click();
			addresseeInput.sendKeys(charSequence);
			waitForElementClickable(firstSuggestedItem);
			firstSuggestedItem.click();
			break;
		case SUBJECT:
			waitForElementVisible(subjectFocusedInput);
			subjectFocusedInput.click();
			subjectFocusedInput.sendKeys(charSequence);
			break;
		case LETTER_TEXT:
			waitForElementVisible(letterTextInput);
			letterTextInput.click();
			letterTextInput.sendKeys(charSequence);
			break;
		default:
			throw new TestDataException("Please implement logic for :\"" + field + "\" - field");
		}
		Logger.info(text + " - was typed in '" + field + "' field");
	}
}
