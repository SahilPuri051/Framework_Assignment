package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.testbase.*;
import com.qa.testdata.readFromExcel;
import com.qa.utilities.DataProviderClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.pages.*;

public class LoginPageTest extends TestBase {
	
	LoginPage loginpage;
	HomePage homepage;
	ExtentTest elog;
	
	public LoginPageTest()
	{
		super();
		
	}
	
	@BeforeClass
	public void init()
	{
		initialize();
		loginpage=new LoginPage();
		elog=extent
				.createTest("LoginPageTest");
	}
	
	@Test(priority=1,groups= {"Username field"})
	public void usernametype()
	{
		String un=loginpage.getUsernameType();
		Assert.assertEquals(un,Constants.usernameType);
		elog.log(Status.PASS,"Username Type is Verified");
	}
	
	@Test(priority=2,groups= {"Username field"})
	public void usernametext()
	{
		String un1=loginpage.getUsernamePlaceholder();
		Assert.assertEquals(un1,Constants.usernamePlaceholder);
		elog.log(Status.PASS,"Username Text is Verified");
	}
	@Test(priority=3,groups= {"Password field"})
	public void passwordtext()
	{
		String pa=loginpage.getPasswordPlaceholder();
		Assert.assertEquals(pa,Constants.passwordPlaceholder);
		elog.log(Status.PASS,"Password Text is Verified");
	}
	
	@Test(priority=4,groups= {"Password field"})
	public void passwordtype()
	{
		String pa1=loginpage.getPasswordType();
		Assert.assertEquals(pa1,Constants.passwordType);
		elog.log(Status.PASS,"Password Type is Verified");
	}
	
	@Test(priority=5,groups= {"Others"})
	public void welcomeMsg()
	{
		Assert.assertEquals(loginpage.getWelcomeMsg(),Constants.WelcomeMsg);
		elog.log(Status.PASS,"Welcome Msg Text is Verified");
	}
	
	@Test(priority=6,groups= {"Others"})
	public void checkTitle()
	{
		Assert.assertEquals(loginpage.getTitle(),Constants.HomePageTitle);
		elog.log(Status.PASS,"HomePage Title is Verified");
	}

	@Test(priority=7,groups= {"Button"})
	public void loginButtonHeight()
	{
		Assert.assertEquals(loginpage.btnheight(),Constants.loginBtnHeight);
		elog.log(Status.PASS,"loginButton Height is Verified");
	}
	
	@Test(priority=8,groups= {"Button"})
	public void loginButtonColor()
	{
		Assert.assertEquals(loginpage.btncolor(),Constants.loginBtnColor);	
		elog.log(Status.PASS,"Login Button Color is Verified");
	}
	
	@Test(priority=9,groups= {"Button"})
	public void loginButtonalign()
	{
		Assert.assertEquals(loginpage.btnalign(),Constants.btnalign);	
		elog.log(Status.PASS,"Login Button Alignment is Verified");
	}
	
	@Test(priority=10,groups= {"Others"})
	public void checkboxinital()
	{
		Assert.assertFalse(loginpage.getcheckboxstate());
		elog.log(Status.PASS,"Checkbox State is Verified");
	}
	
	@Test(priority=11,groups= {"Others"})
	public void checkboxfinal() throws InterruptedException
	{
		loginpage.checkBoxClick();
		Assert.assertTrue(loginpage.checkBoxStatusAfterChecked());
		elog.log(Status.PASS,"Checkbox Click and State is Verified");
	}
	   
	@Test(priority=12,groups= {"Username Field"})
	public void usernameParameters()
	{
	 Assert.assertEquals(loginpage.getUsernamealign(),Constants.usernameAlign);	
	 Assert.assertEquals(loginpage.getUsernamebgColor(),Constants.usernameBgColor);
	 Assert.assertEquals(loginpage.getUsernameSize(),Constants.usernameSize);
	 Assert.assertEquals(loginpage.getUsernamewidth(),Constants.usernameWidth);
	 elog.log(Status.PASS,"Username Box Parameters is Verified");
	}
	
	@Test(priority=13,dataProvider="excelData",dataProviderClass=DataProviderClass.class)
	public void loginErrorMsgTest(String un,String pa) throws InterruptedException
	{
		homepage = loginpage.login(un, pa);
		String[] s=loginpage.returnErrorString();
		Assert.assertEquals(s[0],"Entered username and password do not match.");
		Assert.assertEquals(s[1],"Please check them and try again.");
	}
	
	
	@AfterClass
	public void tearDown()
	{
		extent.flush();
		driver.quit();	
	}
}
