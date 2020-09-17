package utils;
import reporting.Logger;

import java.util.function.Supplier;

public class Waiter {

	public final void start(String message, Supplier<Boolean> waitCondition) {
		while (!waitCondition.get()) {
			if (!waitCondition.get()) {
				Logger.info("Waiting for condition to be true. Condition is: '" + message + "'");
			} else {
				Logger.info("Condition is true. Condition is: '" + message + "'");
			}
		}
	}
}
