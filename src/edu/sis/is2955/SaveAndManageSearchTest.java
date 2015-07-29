package edu.sis.is2955;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * As a user,
 * I would like to save and manage my search histories,
 * So that I can get the latest search update.
 * 
 */

public class SaveAndManageSearchTest {
	private WebDriver driver;
	private String baseUrl;
	
	// Start at the main page for craigslist and log in account
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://pittsburgh.craigslist.org";
		driver.get(baseUrl + "/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
		driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
		driver.findElement(By.id("inputPassword")).sendKeys("abc123456");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
	}
	
	// Given that I am in the main page
	// When I search for something and click "save search" button
	// Then I should get the search key words saved and jump to saved searches page
	@Test
	public void testSaveSearch() {
		try {
			// Get to main page
			driver.get(baseUrl + "/");

			// Search for something
			driver.findElement(By.cssSelector("a.swp > span.txt")).click();
				
			// Click "save search" button
			driver.findElement(By.linkText("save search")).click();
					
			// Check if there's a message tells search was saved
			assertEquals("search was saved.", driver.findElement(By.id("alert")).getText());
				
			// Check if page changed to account page
			String title = driver.getTitle();
			assertEquals("craigslist account", title);
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Given that I am in the account page
	// When I click the off button
	// Then I should get the button change to ons
	@Test
	public void testTurnOnAlert() {
		try {
			// Check the button is off at first
			driver.findElement(By.linkText("searches")).click();
			assertEquals("off", driver.findElement(By.cssSelector("label")).getText());
					
			// Click the button and see if it changes to on
			driver.findElement(By.name("subNotify")).click();
			assertEquals("on", driver.findElement(By.cssSelector("label > b")).getText());
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Given that I am in the account search page
	// When I click the "hits" button
	// Then the results should be sorted
	@Test
	public void testSortSavedSearches() {
		try {
			//	Click "searches" to change to accout search page
			driver.findElement(By.linkText("searches")).click();
					
			//	Click twice to sort hits number from high to low
			driver.findElement(By.xpath("//table[@id='savedsearchlist']/thead/tr/th[5]")).click();
			driver.findElement(By.xpath("//table[@id='savedsearchlist']/thead/tr/th[5]")).click();
					
			//	Compare two hits number, the first should be larger than the next
			int hits1 = Integer.parseInt(driver.findElement(By.id("2541120")).getText());
			int hits2 = Integer.parseInt(driver.findElement(By.id("2649915")).getText());
			assertTrue(hits1 > hits2);
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Given that I am in the account search page
	// When I click the "delete" button
	// Then the results should be deleted
	@Test
	public void testDeleteSavedSearches() {
		try {
			//	Click "searches" to change to account search page
			driver.findElement(By.linkText("searches")).click();
				
			//	Click "delete" button
			driver.findElement(By.xpath("//a[2]/button")).click();
					
			//	Message shows "saved search was delete"
			assertEquals("saved search was deleted.", driver.findElement(By.id("alert")).getText());
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//	After each test, quit driver, since driver cannot quit by itself
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
