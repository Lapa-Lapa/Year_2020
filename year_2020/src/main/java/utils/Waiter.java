package utils;

import org.apache.log4j.Logger;
import pages.BasePage;

import java.util.function.Supplier;

public class Waiter {

	private static final Logger LOGGER = Logger.getLogger(BasePage.class);

	public final void start(String message, Supplier<Boolean> waitCondition) {
		while (!waitCondition.get()) {
			if (!waitCondition.get()) {
				LOGGER.info("Waiting for condition to be true. Condition is: '" + message + "'");
			} else {
				LOGGER.info("Condition is true. Condition is: '" + message + "'");
			}
		}
	}
}
