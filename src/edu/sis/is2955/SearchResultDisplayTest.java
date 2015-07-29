package edu.sis.is2955;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * As a user,
 * I would like to check the search results in different views and sort them by prices,
 * So that I can quickly locate the result I prefer.
 * 
 */

public class SearchResultDisplayTest {
	private WebDriver driver;
	private String baseUrl;
	
	//	Start at the main page for craigslist for each test
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://pittsburgh.craigslist.org";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//	Given that I am in the main page
	//	When I search for something and click "$$$" button
	//	Then I should get the search results sorted from price low to high
	@Test
	public void testSortPriceFromLowToHigh() throws Exception {
		driver.get(baseUrl + "/");
		
		//	Search for word "apt"
		driver.findElement(By.id("query")).sendKeys("apt");
		driver.findElement(By.id("query")).sendKeys(Keys.ENTER);
		
		//	Click "$$$" to sort results
		driver.findElement(By.linkText("$$$")).click();
		try {
			//	Extract the price part in result and change it from string to integer
			String[] priceStr1 = driver.findElement(By.xpath("//p[5]/a/span")).getText().split("[\\D]+");
			String[] priceStr2 = driver.findElement(By.xpath("//p[16]/a/span")).getText().split("[\\D]+");		
			int price1 = Integer.parseInt(priceStr1[1]);
			int price2 = Integer.parseInt(priceStr2[1]);
			
			//	Compare two prices, the price before should be smaller than after one's,
			//	since it sort in ascending order.
			assertTrue(price1 < price2 || price1 == price2);
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//	Given that I am in the main page
	//	When I search for something and click "thumb" button
	//	Then I should get the search results from gallery view to thumb view
	@Test
	public void testChangetoThumbView() throws Exception {
		driver.get(baseUrl + "/");
		
		//	Search for word "apt"
		driver.findElement(By.id("query")).sendKeys("apt");
		driver.findElement(By.id("query")).sendKeys(Keys.ENTER);
		try {
			//	At default view, its element with css "div > img" should not be null
			assertNotNull(driver.findElement(By.cssSelector("div > img")).getText());
			
			//	Change to thumb view
			driver.findElement(By.id("picview")).click();
			
			//	The element with css "img.thumb" should not be null
			assertNotNull(driver.findElement(By.cssSelector("img.thumb")).getText());
			//	Meanwhile, the element with previous css "div > img" should have no vaule
			assertNull(driver.findElement(By.cssSelector("div > img")).getAttribute("value"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//	Given that I am in the main page
	//	When I search for something and click "list" button
	//	Then I should get the search results from gallery view to list view
	@Test
	public void testChangetoListView() throws Exception {
		driver.get(baseUrl + "/");
		
		//	Search for word "apt"
		driver.findElement(By.id("query")).sendKeys("apt");
		driver.findElement(By.id("query")).sendKeys(Keys.ENTER);
		try {
			//	At default view, its element with css "div > img" should not be null
			assertNotNull(driver.findElement(By.cssSelector("div > img")).getText());
			
			//	Change to list view
			driver.findElement(By.id("listview")).click();
			//	There should only have words, so element with css "div > img" should have no vaule
			assertNull(driver.findElement(By.cssSelector("div > img")).getAttribute("value"));
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
