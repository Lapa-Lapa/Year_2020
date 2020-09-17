package business_objects;

import lombok.Data;
import utils.RandomString;

@Data
public class Letter {

	private String addressee;
	private String subject = "Subject: " + RandomString.getRandomString();
	private String letterText = "Letter text: " + RandomString.getRandomString();

	public Letter(String addressee) {
		this.addressee = addressee;
	}
}
