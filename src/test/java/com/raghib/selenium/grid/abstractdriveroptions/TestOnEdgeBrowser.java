package com.raghib.selenium.grid.abstractdriveroptions;

/**
 * Reference:-
 * https://www.youtube.com/watch?v=IyTFUNeQ5Vs&t=47s
 * 
 */

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.raghib.selenium.base.BaseClassToInitializeDriverUsingAbstractDriverOptions;

//ctrl + shift + m -> Shortcut to import the dependency

public class TestOnEdgeBrowser extends BaseClassToInitializeDriverUsingAbstractDriverOptions {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() throws MalformedURLException {
		driver = initializeBrowser("microsoft-edge");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testOnEdge() throws MalformedURLException {
		System.out.println("MicrosoftEdge Browser Test Started");
		driver.get("https://www.google.com");
		System.out.println("Page Title : "+driver.getTitle());
		System.out.println("MicrosoftEdge Browser Test End");
	}
}
