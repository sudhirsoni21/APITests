package com.sudhir.APIRestTestNG.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sudhir.APIRestTestNG.TestUtils.DataProviderClass;
import com.sudhir.APIRestTestNG.api.CustomerAPI;
import com.sudhir.APIRestTestNG.setup.APISetup;

import io.restassured.response.Response;


public class TestCustomerAPI extends APISetup{
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider="dp2")
	public void validateCreateCustomerAPIWithValidData(Hashtable<String, String> data) {
		testLevelLog.get().assignAuthor("Sudhir");
		testLevelLog.get().assignCategory("Regression");
		Response response = CustomerAPI.sendPostRequestToCreateCustomer(data);
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(data.get("expectedStatusCode")));
		Assert.assertEquals(response.path("email"), data.get("expectedEmailId"));
	}
	
	@Test
	public void m() {
		Assert.fail();
	}
	
	/*@Test(dataProviderClass=DataProviderClass.class, dataProvider="dp2")
	public void validateCreateCustomerAPIWithInvalidAuthKey(Hashtable<String, String> data) {
		
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider="dp2")
	public void validateCreateCustomerAPIWithInvalidEmail(Hashtable<String, String> data) {
		
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider="dp2")
	public void validateCreateCustomerAPIWithoutPassingAuthKey(Hashtable<String, String> data) {
		
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider="dp2")
	public void validateCreateCustomerAPIWithInvalidField(Hashtable<String, String> data) {
		
	}
*/
}
