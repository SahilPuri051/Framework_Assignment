package com.qa.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryLogic implements IRetryAnalyzer {
	
	static int count=0; 
	static int max=2; 
	
	public boolean retry(ITestResult result) 
	{
		
	
				if(count<max) 
				{ 
					count++; 
					return true; 
				} 
				return false; 
	} 

}
