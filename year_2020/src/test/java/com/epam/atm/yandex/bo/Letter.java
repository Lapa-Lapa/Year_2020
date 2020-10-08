package com.epam.atm.yandex.bo;

import lombok.Data;
import com.epam.atm.yandex.utils.RandomString;

@Data
public class Letter {

	private String addressee;
	private String subject = "Subject: " + RandomString.getRandomString();
	private String letterText = "Letter text: " + RandomString.getRandomString();

	private Letter(String addressee) {
		this.addressee = addressee;
	}

	private Letter(String addressee, String subject) {
		this.addressee = addressee;
		this.subject = subject;
	}

	/**
	 * Static factory method with
	 * @param addressee - custom value
	 * @return letter with default random subject & letterText
	 */
	public static Letter createLetterWithAddressee(String addressee) {
		return new Letter(addressee);
	}

	/**
	 * Static factory method with
	 * @param addressee - custom value
	 * @param subject - custom value
	 * @return letter with default random letterText
	 */
	public static Letter createLetterWithSubject(String addressee, String subject) {
		return new Letter(addressee, subject);
	}
}
