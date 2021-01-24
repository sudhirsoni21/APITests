package com.sudhir.APIRestTestNG.TestUtils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.sudhir.APIRestTestNG.setup.APISetup;

public class DataProviderClass extends APISetup{
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m) {
		String sheetName = m.getName();
		
		int rows = excel.getRowCount(sheetName);//3
		int cols = excel.getColumnCount(sheetName);//2
		
		Object[][] data = new Object[rows - 1][1]; //Object[2][1]
		
		Hashtable<String, String> table = null;
		for (int rowNum = 2; rowNum <= rows; rowNum++) {//rows=3
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {//cols=2
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum,rowNum ));
				data[rowNum - 2][0] = table;//{endPoint=/customer,expectedCode=200}
			}
		}
		return data;
		// System.out.println(data); return data;
	}
	
	@DataProvider(name="dp2")
	public  Object[][] getData2(Method m) {

		System.out.println(configProperty);
		System.out.println("SheetName-->"+configProperty.getTestDataSheet());
		String sheetName=configProperty.getTestDataSheet();
		int rows = excel.getRowCount(sheetName);//100
		String testName = m.getName();
		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excel.getCellData(sheetName, 0, testCaseRowNum);
			//System.out.println("TestCase name in excel-->"+testCaseName);
			if (testCaseName.equalsIgnoreCase(testName))
				break;

		}// Checking total rows in test case

		int dataStartRowNum = testCaseRowNum + 2;//dataStartRowNum=8

		int testRows = 0;
		while (!excel.getCellData(sheetName, 0, dataStartRowNum + testRows).equals("endOfTestData")) {

			testRows++;
		}
		// Checking total cols in test case

		//System.out.println("Total no of rows:"+testRows);
		int colStartColNum = testCaseRowNum + 1;//7
		int testCols = 0;

		while (!excel.getCellData(sheetName, testCols, colStartColNum).equals("")) {

			testCols++;

		}
		
		Object[][] data = new Object[testRows][1];

		int i = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

			Hashtable<String, String> table = new Hashtable<String, String>();
		
			for (int cNum = 0; cNum < testCols; cNum++) {

				String colName = excel.getCellData(sheetName, cNum, colStartColNum);
				String testData = excel.getCellData(sheetName, cNum, rNum);
				table.put(colName, testData);
			}

			data[i][0] = table;
			i++;
		}

		return data;
	}

}

