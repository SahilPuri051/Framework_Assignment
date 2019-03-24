package com.qa.utilities;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import com.google.common.io.Files;
import com.qa.testbase.*;

public class GetScreenshot extends TestBase {
		
		public static void getshot(ITestResult result,String name)
		{
			try
			{
			TakesScreenshot ss=(TakesScreenshot)driver;
			File src=ss.getScreenshotAs(OutputType.FILE);
			Files.copy(src,new File(System.getProperty("user.dir")+"/Result_Screenshots/"+result.getMethod().getMethodName()+"_"+name+"_"+System.currentTimeMillis()+".png"));
			}
			catch(IOException e) {
				System.out.println("File Path is Wrong *******************");
			}
		}

}
