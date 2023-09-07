package com.raghib.selenium.grid.desiredcapabilities;

/**
 * Reference:-
 * https://www.youtube.com/watch?v=IyTFUNeQ5Vs&t=47s
 * https://www.youtube.com/watch?v=KtSguNmZ5hA
 * 
 */

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.raghib.selenium.base.BaseClassToInitializeDriverUsingDesiredCapabilities;

//ctrl + shift + m -> Shortcut to import the dependency

public class TestOnChromeBrowser extends BaseClassToInitializeDriverUsingDesiredCapabilities {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws MalformedURLException {
		driver = initializeBrowser("chrome");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testOnChrome() throws MalformedURLException {
		System.out.println("Chrome Browser Test Started");
		driver.get("https://www.google.com");
		System.out.println("Page Title : "+driver.getTitle());
		System.out.println("Chrome Browser Test End");
	}
}