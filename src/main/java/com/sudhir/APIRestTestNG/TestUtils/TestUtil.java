package com.sudhir.APIRestTestNG.TestUtils;

import com.sudhir.APIRestTestNG.setup.APISetup;

import io.restassured.specification.RequestSpecification;

public class TestUtil extends APISetup{
	
	public static RequestSpecification setFormParams(String arguments, RequestSpecification reqSpecs) {
		String[] listOfArguments = arguments.split(";");
		for(String singleArgument: listOfArguments ) {
			String[] keyValue = singleArgument.split(":");
			{
				String key = keyValue[0];
				String value = keyValue[1];
				reqSpecs.formParam(key, value);			
			}
		}
		return reqSpecs;
	}

}
