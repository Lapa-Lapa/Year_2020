package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomString {

	private RandomString() {
	}

	public static String getRandomString() {
		return RandomStringUtils.randomAlphanumeric(15);
	}
}
