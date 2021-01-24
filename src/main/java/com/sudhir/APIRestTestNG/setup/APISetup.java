package com.sudhir.APIRestTestNG.setup;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Method; //Part of Java Reflection concept

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.sudhir.APIRestTestNG.TestUtils.ConfigProperty;
import com.sudhir.APIRestTestNG.TestUtils.ExcelReader;
import com.sudhir.APIRestTestNG.TestUtils.ExtentManager;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class APISetup {
	
	public static ConfigProperty configProperty;
	public static ExtentReports extentReport;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/testData/TestData.xlsx");
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();
	
	@BeforeSuite
	public void beforeSuite() {
		configProperty = ConfigFactory.create(ConfigProperty.class);
		RestAssured.baseURI=configProperty.getBaseURI();
		RestAssured.basePath=configProperty.getBasePath();
		extentReport = ExtentManager.GetExtent(configProperty.getTestReportPath() + configProperty.getTestReportName());
	}
	
	@BeforeClass
	public void beforeClass() {
		ExtentTest classLevelTest = extentReport.createTest(getClass().getSimpleName());
		classLevelLog.set(classLevelTest);
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		ExtentTest test = classLevelLog.get().createNode(method.getName());
		testLevelLog.set(test);		
		testLevelLog.get().info("Test Case :- " + method.getName() + " execution started");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result, Method method) {
		if (result.getStatus()==ITestResult.SUCCESS) {
			testLevelLog.get().pass("Test Case :- " +method.getName()+ " is passed");
		} else if (result.getStatus()==ITestResult.FAILURE) {
			testLevelLog.get().fail("Test Case :- " +method.getName()+ " is failed");
		} else if (result.getStatus()==ITestResult.SKIP) {
			testLevelLog.get().skip("Test Case :- " +method.getName()+ " is skipped");
		}
		extentReport.flush();
	}
	
	@AfterSuite
	public void afterSuite() {
		
	}
	
	/*
	 * Authorization
	 */
	public static RequestSpecification setRequestSpecification() {
		RequestSpecification spec = given().auth().basic(configProperty.getSecreatKey(), "");
		testLevelLog.get().info("Request Specification set");
		return spec;
	}

}
