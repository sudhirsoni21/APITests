package com.sudhir.APIRestTestNG.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sudhir.APIRestTestNG.TestUtils.DataProviderClass;
import com.sudhir.APIRestTestNG.setup.APISetup;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestCreateCustomerAPI extends APISetup{
	/*
	 * URI - https://api.stripe.com/v1/customers
	 * methodType - Post
	 * argument - 
	 * 			description - 
	 * 			email -
	 * 			balance - 
	 * Authorize - sk_test_51I4UOLJxcDcx8HbOuT7oCE1RKESqR094xYVT6e0qF4HyrF8KLv5TznxLq7Mda1lIlcSovJThgn8zm6StGOlywoj3008kvN5hxO
	 */
	@Test(dataProviderClass = DataProviderClass.class, dataProvider="dp")
	public void validateCreateCustomerAPI(Hashtable<String, String> data) {
		testLevelLog.get().assignAuthor("Sudhir");
		testLevelLog.get().assignCategory("Regression");
		RequestSpecification spec = setRequestSpecification()
		.formParam("email", data.get("email"))
		.formParam("description", data.get("description"))
		.formParam("balance",  data.get("balance"))
		.log().all();
		
		System.out.println("============================================");
		
		Response response = spec.post("customers");
		testLevelLog.get().info(response.asString());
		response.prettyPrint();
		Assert.assertEquals(response.statusCode(),200);
		
		System.out.println("Email using path method : " + response.path("email"));
		System.out.println("description using path method : " + response.path("description"));
		System.out.println("balance using path method : " + response.path("balance"));
		
		JsonPath responseJSON = new JsonPath(response.asString());
		System.out.println("Email using JsonPath : " + responseJSON.get("email"));
		System.out.println("description using JsonPath : " + responseJSON.get("description"));
		System.out.println("balance using JsonPath : " + responseJSON.get("balance"));
	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider="dp")
	public void fetchCustomer(Hashtable<String, String> data) {
		testLevelLog.get().assignAuthor("Sudhir");
		testLevelLog.get().assignCategory("Regression");
		RequestSpecification spec = setRequestSpecification().formParam("limit", data.get("limit")).log().all();
		
		System.out.println("============================================");
		
		Response response = spec.request(data.get("requestType"), data.get("endPoint"));
		response.prettyPrint();
		ArrayList<String> listofIds = response.path("data.id");
		ArrayList<String> listofCreated = response.path("data.created");
		ArrayList<String> listofDefaultPaymentMethod = response.path("data.invoice_settings.default_payment_method");
		System.out.println("List of Ids: " + listofIds);
		System.out.println("List of created: " + listofCreated);
		System.out.println("List of DefaultPaymentMethod: " + listofDefaultPaymentMethod);
		System.out.println("Size of data: " + response.path("data.size()"));
		System.out.println("Total fields inside first object of data: " + response.path("data[0].size()"));
		
		int dataSize = response.path("data.size()");
		for(int i=0; i < dataSize; i++) {
			if (response.path("data["+i+"].invoice_prefix").equals("B079F878")) {
				String id = response.path("data["+i+"].id");
				System.out.println("Id for cutomer having invoice_prefix = B079F878 is: " + id);
				break;
			}
		}
	}
}
