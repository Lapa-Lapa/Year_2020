package webdriver;

public enum WebDriverTypes {
	CHROME, FIREFOX, NOT_SUPPORTED_DRIVER;

	public static WebDriverTypes get(String browserName) {
		switch (browserName) {
		case "chrome":
			return CHROME;
		case "firefox":
			return FIREFOX;
		default:
			return NOT_SUPPORTED_DRIVER;
		}
	}
}
