package com.raghib.selenium.base;

/**
 * Reference:-
 * https://www.youtube.com/watch?v=IyTFUNeQ5Vs&t=47s
 * https://www.youtube.com/watch?v=KtSguNmZ5hA
 * 
 */

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClassToInitializeDriverUsingDesiredCapabilities {
	
	DesiredCapabilities dc = new DesiredCapabilities();
	WebDriver driver = null;
	
	public WebDriver initializeBrowser(String browserName) throws MalformedURLException {

		if (browserName.equals("chrome")) {
			dc.setBrowserName("chrome");

		} else if (browserName.equals("microsoft-edge")) {
			dc.setBrowserName("MicrosoftEdge");
			
		} else if (browserName.equals("firefox")) {
			dc.setBrowserName("firefox");			

		}
		
		driver = new RemoteWebDriver(new URL("http://localhost:4444"), dc);
		driver.manage().window().maximize();

		return driver;
	}
}