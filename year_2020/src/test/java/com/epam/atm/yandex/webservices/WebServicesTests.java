package com.epam.atm.yandex.webservices;

import com.epam.atm.yandex.reporting.Logger;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import org.json.simple.parser.JSONParser;

import org.junit.Before;
import org.junit.Test;

public class WebServicesTests {

	@Before
	public void initTest() {
		RestAssured.baseURI = "https://www.sephora.com";
	}
 	@Test
	public void checkStatusCode() {
		int statusCode = given().get("/api/users/profiles/current/full").andReturn().getStatusCode();
		int expectedStatusCode = 200;
		Logger.info(String.format("statusCode ACTUAL VALUE:   - %1$s", statusCode));
		Logger.info(String.format("statusCode EXPECTED VALUE: - %1$s", expectedStatusCode));
		Assert.assertEquals(statusCode, expectedStatusCode);
	}
	@Test
	public void checkResponseBody() {
		String responseBody = given().get("/api/users/profiles/current/full").getBody().asString();
		try {
			JSONObject jsonData = (JSONObject) new JSONParser().parse(responseBody);
			JSONObject basket = (JSONObject) jsonData.get("basket");
			String maxSamplesAllowedPerOrder = basket.get("maxSamplesAllowedPerOrder").toString();
			String expectedMaxSamplesAllowedPerOrder = "2";
			Logger.info(String.format("maxSamplesAllowedPerOrder ACTUAL VALUE:   - %1$s", maxSamplesAllowedPerOrder));
			Logger.info(String.format("maxSamplesAllowedPerOrder EXPECTED VALUE: - %1$s", expectedMaxSamplesAllowedPerOrder));
			Assert.assertEquals(maxSamplesAllowedPerOrder,expectedMaxSamplesAllowedPerOrder);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
