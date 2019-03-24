package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.testbase.*;

public class HomePage extends TestBase {

	// Page Factory (Object Repository)
	@FindBy(xpath="//span[@class='icon Analyze-ACE']")
	WebElement ACEbtn;
	
	@FindBy(xpath="//span[@class='icon profile']")
	WebElement ProfileBtn;
	
	@FindBy(xpath="//a[text()='Log Out']")
	WebElement Logoutbtn;
	
	
	public HomePage()
	{
		PageFactory.initElements(driver,this);
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public void clickACE() 
	{
		ACEbtn.click();
	}
	
	public ACEPage goToACE()
	{
		clickACE();
		return new ACEPage();
	}
	
	public void logout()
	{
		ProfileBtn.click();
		Logoutbtn.click();
	}
	
	
}
