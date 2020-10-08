package com.epam.atm.yandex.reporting;

/**
 * Class for logging
 */
public class Logger {

	private Logger() {
	}

	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Logger.class);

	public static void error(String message) {
		log.error(message);
	}

	public static void info(String message) {
		log.info(message);
	}

	public static void debug(String message) {
		log.debug(message);
	}

	public static void warn(String message) {
		log.warn(message);
	}

	public static void trace(String message) {
		log.trace(message);
	}
}
