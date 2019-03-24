package com.qa.testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {


	static Properties prop;
	
	// Returns desired webdriver instance 
	public static WebDriver createWebdriver(WebDriver driver) {
		
	    String browser=prop.getProperty("browser");
		if(browser.contains("chrome")) 
		{ 
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", Constants.chromedownloadPath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(options);
			 
		} 
		else if(browser.contains("firefox")) 
		{ 
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
			FirefoxProfile firefoxProfile=new FirefoxProfile();
			firefoxProfile.setPreference("browser.download.folderList",2);
			firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
			firefoxProfile.setPreference("browser.download.dir",Constants.firefoxdownloadPath);
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","image/png,image/jpeg");
			firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
			firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
			firefoxProfile.setPreference("browser.download.manager.useWindow", false);
			firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
			
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(firefoxProfile);
			driver=new FirefoxDriver(options);
			
		}
		
		else if(browser.contains("Edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\Users\\SAHIL\\eclipse-workspace\\BlueOptima_Project1\\Drivers\\MicrosoftWebDriver.exe");
			driver= new EdgeDriver();
			
		}
		return driver; 
		
	}
	
	// Initializes Config.properties File
	public static Properties initializeConfig()
	{
		try 
		{ 
			FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"/config/config.properties"); 
			prop=new Properties(); 
			prop.load(file); 
		} 
		catch(IOException e) 
		{ 
			e.printStackTrace(); 
		} 
		 
		return prop;
	}

}
