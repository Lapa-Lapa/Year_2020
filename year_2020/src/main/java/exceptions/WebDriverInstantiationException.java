package exceptions;

/**
 * Thrown to indicate that an invalid or unsupported browser name was used.
 *
 * @author Darya_Tarelko
 */
public class WebDriverInstantiationException extends RuntimeException {

	public WebDriverInstantiationException(String message) {
		super(message);
	}
}
