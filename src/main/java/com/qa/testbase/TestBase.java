package com.qa.testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase {
	public static WebDriver driver; 
	public static Properties prop; 
	public static Logger logger;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;

	public TestBase() 
		{	 
			//Initializing Properties File for Environment variables
			prop=WebDriverFactory.initializeConfig();
			
			// Initializing Log4j logging Files
			logger=LogReportInitializer.logInitialize(logger);
			
			// Initializing Extent Reports Files
			extent=LogReportInitializer.reportInitializer(reporter,extent);
				
		} 
	
	public void initialize()
	{
		driver = Driver.getCurrentDriver(driver);
		//driver=new ChromeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.implicitWaitTime,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.pageLoadWaitTime, TimeUnit.SECONDS);
	}

}
