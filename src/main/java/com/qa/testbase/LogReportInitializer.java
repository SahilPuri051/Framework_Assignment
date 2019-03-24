package com.qa.testbase;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class LogReportInitializer {
	static int logcheck=0;
	static int reportcheck=0;
	
	public static Logger logInitialize(Logger logger)
	{
		logcheck++;
		if(logcheck<2)
		{
			logger=Logger.getLogger(LogReportInitializer.class);
			PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/log4j.properties");
			return logger;
		}
		return logger;
	}

	public static ExtentReports reportInitializer(ExtentHtmlReporter reporter,ExtentReports extent)
	{
		reportcheck++;
		if(reportcheck<2)
		{
		reporter=new ExtentHtmlReporter("./Extent-Reports/BlueOptima.html");
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("User Name","Sahil Puri");
		extent.setSystemInfo("Environment","BlueOptim.QA");
		}
		return extent;
	}

}
