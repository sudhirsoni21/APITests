package com.sudhir.APIRestTestNG.api;

import java.util.Hashtable;

import com.sudhir.APIRestTestNG.TestUtils.TestUtil;
import com.sudhir.APIRestTestNG.setup.APISetup;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class CustomerAPI extends APISetup{
	
	public static Response sendPostRequestToCreateCustomer(Hashtable<String, String> data)
	{
		Response response = TestUtil.setFormParams(data.get("arguments"), setRequestSpecification()).post(data.get("endpoint"))
		.then().extract().response();
		return response;
	}
	
	public static Response sendGetRequestToListAllCustomers(Hashtable<String, String> data, RequestSpecification requestSpecification)
	{
		Response response= requestSpecification.get(data.get("endpoint"))
				.then().extract().response();
				return response;
		
	}
	public static Response sendDeleteMethodWithValidData(Hashtable<String, String> data, RequestSpecification requestSpecification)
	{
		Response response=requestSpecification.delete(data.get("endpoint"))
				.then().extract().response();
		return response;
	}

}
