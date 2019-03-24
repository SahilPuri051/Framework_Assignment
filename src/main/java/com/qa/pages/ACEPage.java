package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.testbase.*;

public class ACEPage extends TestBase {

	JavascriptExecutor je;
	WebDriverWait wait;
	
	public ACEPage()
	{
		je=(JavascriptExecutor)driver;
		PageFactory.initElements(driver,this);
		wait=new WebDriverWait(driver,10);
	}
	
	// Page Factory (Object Repository)
	
	@FindBy(xpath="//a[@data-target='developers']")
	WebElement developersbtn;
	
	@FindBy(xpath="//iframe[contains(@export-big-data,'exportBigData')]")
	WebElement bigFrame;
	
	@FindBy(xpath="//*[name()='g'][contains(@class,'highcharts-button ')]")
	WebElement contextMenuchrome;
	
	@FindBy(xpath="//*[@class='highcharts-button-symbol']")
	WebElement contextMenu;
	
	@FindBy(xpath="//div[text()='Download PNG image']")
	WebElement downloadPng;
	
	@FindBy(xpath="//iframe[contains(@chart-name,'.large')]")
	WebElement bigFrame2;
	
	@FindBy(xpath="//div[@id='chart']")
	WebElement Chart;
	
	@FindBy(xpath="//h4[@class='chartHeader']/span[1]")
	WebElement chartTitle;
	
	public void clickonDev()
	{
		developersbtn.click();
	}
	
	public void enterFrame(int num)
	{
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(num));
	//	driver.switchTo().frame(num);
		Chart.click();
	}
	
	public void exitFrame()
	{
		driver.switchTo().defaultContent();
	}
	
	public void enterBigFrame()
	{
		exitFrame();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(bigFrame2));
		//driver.switchTo().frame(bigFrame2);
	}
	
	public void enterBigFrameAndClickMenu()  throws InterruptedException
	{ 
		je.executeScript("window.scrollBy(0,800)");
		driver.switchTo().frame(bigFrame);
		Thread.sleep(2000);
		je.executeScript("arguments[0].scrollIntoView(true);",contextMenu);		
		contextMenu.click();
	}
	
	public void downloadPng() {
		downloadPng.click();
		exitFrame();
	}
	
	public String returnExpectedFileName()
	{
		return chartTitle.getText();
	}
	
	public void consolePrint(String expectedFileName, String actualfileName)
	{
		System.out.println("Expected File Name is :"+expectedFileName);
		System.out.println("Actual File Name is:"+actualfileName);
	}
	
	public void consolePrintDate(String date)
	{
		System.out.println("Date is :"+date);
	}

	
}
