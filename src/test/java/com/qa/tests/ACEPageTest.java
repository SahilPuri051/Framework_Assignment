package com.qa.tests;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.testbase.Constants;
import com.qa.testbase.TestBase;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.filehandling.GetLatestFile;
import com.qa.pages.ACEPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ACEPageTest extends com.qa.testbase.TestBase {
	
	public static String expectedFileName="";
	public static String actualfileName="";
	public static String TodaysDate="";
	public static BufferedImage actualImage =null;
	public static BufferedImage expectedImage =null;
	public static File getLatestFile = null;
	
	LoginPage loginpage;
	HomePage homepage;
	ACEPage analyzepage;
	ExtentTest elog;
	
	public ACEPageTest()
	{
		super();
	}
	
	@BeforeClass
	public void init() throws InterruptedException
	{
		initialize();
		loginpage = new LoginPage();
		homepage=loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		analyzepage=homepage.goToACE();
		elog=extent
				.createTest("ACEChartsPageTest");
	}
	
	
	@Test(priority=15)
	public void clickOnChart()
	{
		analyzepage.clickonDev();
		analyzepage.enterFrame(Constants.Chart_Number);
		analyzepage.exitFrame();
		elog.log(Status.PASS,"Chart Click Test is Verified");
	}
	
	
	@Test(priority=16)
	public void downloadPngTest() throws InterruptedException
	{
		analyzepage.enterBigFrameAndClickMenu();
		analyzepage.downloadPng();
		elog.log(Status.PASS,"Download PNG Test is Verified");
	}
	
	@Test(priority=17)
	public void downloadedFileNameAssert() throws IOException, InterruptedException
	{
		// Assert Expected File Name and Actual File Name
		getfileNames();
	    Assert.assertTrue(actualfileName.contains(expectedFileName), "Downloaded file name is not matching with expected file name");
	    Assert.assertTrue(actualfileName.contains(".png"), "Downloaded file name is not matching with expected file name");
	    analyzepage.consolePrint(expectedFileName,actualfileName);
	    elog.log(Status.PASS,"Download File Assert By Name Test is Verified");
	}
	
	
	@Test(priority=19)
	public void downloadedFileDateAssert() throws IOException
	{
	 // Assert Expected Date (Today's) with Time
	    getTodaysDate();
	    analyzepage.consolePrintDate(TodaysDate);
	    Assert.assertTrue(actualfileName.contains(TodaysDate), "Downloaded file Date is not matching with expected file name");
	    elog.log(Status.PASS,"Download File Assert By Date Test is Verified");
	    //Desktop.getDesktop().open(getLatestFile);
	}
	
	
	@Test(priority=20)
	public void imageComparison() throws IOException
	{
	
		getWebpageScreenshot();
		if(prop.getProperty("browser").contains("chrome"))
		{
			expectedImage = ImageIO.read(GetLatestFile.getLatestFilefromDir(Constants.chromedownloadPath));
		}
		else
		{
			expectedImage = ImageIO.read(GetLatestFile.getLatestFilefromDir(Constants.firefoxdownloadPath));
		}
		
	    ImageDiffer imgDiff = new ImageDiffer();
	    ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
	    Assert.assertTrue(diff.hasDiff(),"Images are Same");
	    elog.log(Status.PASS,"Image Comparison Test is Verified");
	}
	
	@AfterClass
	public void tearDown()
	{
		extent.flush();
		driver.quit();
	}

	
	
	//** Helping Methods
	public void getfileNames()
	{
	expectedFileName=analyzepage.returnExpectedFileName();
	if(prop.getProperty("browser").contains("chrome"))
	{
    getLatestFile = GetLatestFile.getLatestFilefromDir(Constants.chromedownloadPath);
	}
	else
	{
	getLatestFile = GetLatestFile.getLatestFilefromDir(Constants.firefoxdownloadPath);
	}
    actualfileName = getLatestFile.getName();
	}
	
	
	public String getTodaysDate()
	{
	Date objDate = new Date();
    String strDateFormat = "yyyy-MM-dd kk-mm";
    SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
    TodaysDate=objSDF.format(objDate);
    return TodaysDate;
	}	
	
	
	public void getWebpageScreenshot() throws IOException
	{
		analyzepage.enterBigFrame();
		Screenshot logoImageScreenshot =new AShot().takeScreenshot(driver,driver.findElement(By.xpath("//div[@id='chart']/div[@class='highcharts-container ']")));
		actualImage = logoImageScreenshot.getImage();
		ImageIO.write(actualImage,"PNG",new File(System.getProperty("user.dir")+"/Test_Screenshots/test_shot1.png"));
	}
	
}