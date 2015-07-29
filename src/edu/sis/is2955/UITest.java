package edu.sis.is2955;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * As a developer,
 * I would like to set UI through cookies. 
 * So that I can switch to different UIs by changing the value in cookies.
 *
 */

public class UITest {
	private WebDriver driver;
	private String baseUrl;
	
    //	Start at the main page for craiglist for each test
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://pittsburgh.craigslist.org";
		driver.get(baseUrl + "/");
	}

	//	Given that I am at the main page
	//	When I change the cl_fmt's value to mobile in cookies
	//	Then I should get an mobile UI when I reload the page
	@Test
	public void testChangeToMobileUI() {
		//	Using JS to set cookies
		((JavascriptExecutor) driver).executeScript("document.cookie = 'cl_fmt=mobile'");
		
		//	Reload the page
		driver.get(baseUrl + "/");
		
		//	There should have an menu for mobile
		assertNotNull(driver.findElement(By.cssSelector("div.mobile-menu-button.mobile-only")).getText());
	}
	
	
	//	After each test, quit driver, since driver cannot quit by itself
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
