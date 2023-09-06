package com.raghib.selenium.grid.desiredcapabilities;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.raghib.selenium.base.BaseClassToInitializeDriverUsingDesiredCapabilities;

//ctrl + shift + m -> Shortcut to import the dependency

public class TestOnFirefoxBrowser extends BaseClassToInitializeDriverUsingDesiredCapabilities {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws MalformedURLException {
		driver = initializeBrowser("firefox");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testOnFirefox() throws MalformedURLException {
		System.out.println("Firefox Browser Test Started");
		driver.get("https://www.google.com");
		System.out.println("Page Title : "+driver.getTitle());
		System.out.println("Firefox Browser Test End");
	}
}
