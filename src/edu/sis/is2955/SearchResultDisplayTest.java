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
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://pittsburgh.craigslist.org";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testSortPriceFromLowToHigh() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("query")).sendKeys("apt");
		driver.findElement(By.id("query")).sendKeys(Keys.ENTER);
		driver.findElement(By.linkText("$$$")).click();
		try {
			String[] pricestr1 = driver.findElement(By.xpath("//p[5]/a/span")).getText().split("[\\D]+");
			String[] pricestr2 = driver.findElement(By.xpath("//p[16]/a/span")).getText().split("[\\D]+");		
			int price1 = Integer.parseInt(pricestr1[1]);
			int price2 = Integer.parseInt(pricestr2[1]);
			assertTrue(price1 < price2 || price1 == price2);
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	@Test
	public void testChangetoThumbView() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("query")).sendKeys("apt");
		driver.findElement(By.id("query")).sendKeys(Keys.ENTER);
		try {
			assertNotNull(driver.findElement(By.cssSelector("div > img")).getText());
			
			driver.findElement(By.id("picview")).click();
			assertNotNull(driver.findElement(By.cssSelector("img.thumb")).getText());
			assertNull(driver.findElement(By.cssSelector("div > img")).getAttribute("value"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	@Test
	public void testChangetoListView() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("query")).sendKeys("apt");
		driver.findElement(By.id("query")).sendKeys(Keys.ENTER);
		try {
			assertNotNull(driver.findElement(By.cssSelector("div > img")).getText());
			
			driver.findElement(By.id("listview")).click();
			assertNull(driver.findElement(By.cssSelector("div > img")).getAttribute("value"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
