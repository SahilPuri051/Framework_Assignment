package com.qa.utilities;

import org.testng.annotations.DataProvider;

import com.qa.testdata.readFromExcel;

public class DataProviderClass {

	@DataProvider(name="excelData")
    public static Object[][] loginData() throws Exception
    {
		Object[][] testObjArray = readFromExcel.getTableArray(System.getProperty("user.dir")+"/config/test.xls","Sheet1");
		 
	    return (testObjArray);
     
    }
     
}
