package com.sudhir.APIRestTestNG.listners;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sudhir.APIRestTestNG.setup.APISetup;


public class CustomerListerner extends APISetup implements ITestListener  {

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	public void onStart(ITestContext context) {
		
		
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		String excepionMessage = Arrays.toString(arg0.getThrowable().getStackTrace());
		testLevelLog.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");


		String failureLogg = "This Test case got Failed";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testLevelLog.get().log(Status.FAIL, m);
		
		extentReport.flush();
		
	}

	public void onTestSkipped(ITestResult arg0) {
		ExtentTest test = classLevelLog.get().createNode(arg0.getName());
		testLevelLog.set(test);
		testLevelLog.get().skip("This test case got skipped");
		extentReport.flush();
		
	}
	
	public void onTestStart(ITestResult arg0) {
		ExtentTest test = classLevelLog.get().createNode(arg0.getName());
		testLevelLog.set(test);		
		testLevelLog.get().info("<b>"+"Test Case :- " + arg0.getName() + " execution started"+"</b>");
		
	}

	public void onTestSuccess(ITestResult arg0) {
		ExtentTest test = classLevelLog.get().createNode(arg0.getName());
		testLevelLog.set(test);
		testLevelLog.get().pass("<b>"+"This test case got passed"+"</b>");
		extentReport.flush();
		
	}

}
