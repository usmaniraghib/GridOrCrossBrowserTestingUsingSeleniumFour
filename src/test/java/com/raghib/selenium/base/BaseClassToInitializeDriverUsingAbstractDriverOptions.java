package com.raghib.selenium.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClassToInitializeDriverUsingAbstractDriverOptions {
	
	public WebDriver driver;
	
	public WebDriver initializeBrowser(String browserName) throws MalformedURLException {
		
		@SuppressWarnings("rawtypes")
		AbstractDriverOptions options = null;
		
		if(browserName.equals("chrome")) {
			options = new ChromeOptions();
			options.setCapability("browserName", "chrome");
			// Showing a test name instead of the session id in the Grid UI
			options.setCapability("se:name", "My simple test"); 
			
		} else if(browserName.equals("microsoft-edge")) {			
			options = new EdgeOptions();
			options.setCapability("browserName", "MicrosoftEdge");
			
		} else if(browserName.equals("firefox")) {			
			options = new FirefoxOptions();
			options.setCapability("browserName", "firefox");
			
		}
		
		driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);		
		driver.manage().window().maximize();
		
		return driver;		
	}
}
