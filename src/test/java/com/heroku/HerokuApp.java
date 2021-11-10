package com.heroku;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.heroku.ClientCredentials;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HerokuApp {
	String accessToken;

	@Test(priority = 0)
	void getAccessToken_POST() {
		baseURI = "https://restful-booker.herokuapp.com";

		// Building the PostRequest-->
		RequestSpecification request = given();
		request.header("Content-Type", ClientCredentials.Content_type);
		// Body-->
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", ClientCredentials.username);
		jsonObject.put("password", ClientCredentials.password);
		request.body(jsonObject.toJSONString());
		Response response = request.post("/auth");
		// Verifying the http statusCode
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
		// Extracting the value from Response
		JsonPath jsonPath = new JsonPath(response.asString());
		accessToken = jsonPath.get("token").toString();
		System.out.println("AccessToken--> "+accessToken);
	}

	@Test(priority = 1)
	void createBooking_POST() {

		baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification request = given();
		// Building the PostRequest-->
		request.header("Content-Type", ClientCredentials.Content_type);

		request.body(TestData.getDataForPost());

		Response response = request.post("/booking");
		// Verifying the http statusCode
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
		// Extracting the value from Response
		JsonPath jsonPath = new JsonPath(response.asString());
		int id = jsonPath.get("bookingid");
		System.out.println("Booking ID--->" + id);
	}

	@Test(priority = 2)
	void GetBooking_GET() {
		// System.out.println("accessToken-->" + accessToken);
		baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification request = given();
		// Building the GetRequest-->
		request.header("Content-Type", ClientCredentials.Content_type);
		Response response = request.get("/booking/1");
		// Verifying the http statusCode
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
		// Extracting the value from Response

		JsonPath jsonPath = new JsonPath(response.asString());
		String checkInDate = jsonPath.get("bookingdates.checkin");
		String checkOutDate = jsonPath.get("bookingdates.checkout");
		System.out.println("CheckinDate-->" + checkInDate);
		System.out.println("checkOutDate-->" + checkOutDate);

	}

}
