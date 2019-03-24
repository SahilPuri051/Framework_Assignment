package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.testbase.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.pages.ACEPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class HomePageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	ACEPage acepage;
	ExtentTest elog;
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeClass
	public void init() throws InterruptedException
	{
		initialize();
		loginpage=new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		elog=extent
				.createTest("HomePageTest");
	}
	
	@Test(priority=14)
	public void checkHomePageTitle()
	{
		Assert.assertEquals(homepage.getPageTitle(),Constants.HomePageTitle);
		homepage.clickACE();
		elog.log(Status.PASS,"Home Page Title is Verified");
	}
	
	@AfterClass
	public void tearDown()
	{
	//	homepage.logout()
		extent.flush();
		driver.quit();
	
	}
}
