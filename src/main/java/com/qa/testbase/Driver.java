package com.qa.testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class Driver {

		
		public static WebDriver getCurrentDriver(WebDriver driver) 
		
		{
			driver = WebDriverFactory.createWebdriver(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
			return driver;
		
		}

}
