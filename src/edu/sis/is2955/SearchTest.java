package edu.sis.is2955;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * As a user, I would like to search on craigslist. 
 * So that I can view the relevant and interesting posts only.
 *
 */

public class SearchTest {
	private WebDriver driver;
	
	//      Start at the search page of craiglist for each test
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://pittsburgh.craigslist.org/search/sss");
	}

	//	Given that I want to search an English query
	//	When I input the English query and submit it
	//	Then I see that relevant posts show up, with the query as part of title
	@Test
	public void testEnglishQuery() {
		//	Input an English query
		driver.findElement(By.id("query")).sendKeys("book");
		
		//	Submit the query
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		
		//	Find the first result's title, it should contain "book", regardless of cases
		WebElement firstResult = driver.findElement(By.xpath("/html/body/section/div[2]/form/div[2]/div[3]/p[1]/span/span[2]/a"));
		assertTrue(firstResult.getText().toLowerCase().contains("book"));
	}
	
	//	Given that I want to search an Chinese query
	//	When I input the Chinese query and submit it
	//	Then I see that posts show up, without the query as part of title
	//	We think this is a defects, since it cannot recognize the chinese words, but it still give results.
	@Test
	public void testChineseQuery() {
		//	Input an Chinese query
		driver.findElement(By.id("query")).sendKeys("��");
		
		//	Submit the query
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		
		//	Find the first result's title, it should have result, but the result doesn't contain the query
		WebElement firstResult = driver.findElement(By.xpath("/html/body/section/div[2]/form/div[2]/div[3]/p[1]/span/span[2]/a"));
		assertTrue(firstResult.isDisplayed() && !firstResult.getText().contains("Êé"));
	}
	
	//	Given that I want to search an English-Chinese query
	//	When I input the English-Chinese query and submit it
	//	Then I see that posts show up, but it only contain English part in the title, not include the chinese part
	@Test
	public void testEngChinQuery() {
		//	Input an conbine English and Chinese query
		driver.findElement(By.id("query")).sendKeys("Êébook");
		
		//	Submit the query
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		
		//	Find the first result's title, it should have result, but the result doesn't contain the chinese letter, only contains English letter
		WebElement firstResult = driver.findElement(By.xpath("/html/body/section/div[2]/form/div[2]/div[3]/p[1]/span/span[2]/a"));
		assertTrue(firstResult.isDisplayed() && !firstResult.getText().contains("Êé"));
		assertTrue(firstResult.isDisplayed() && firstResult.getText().toLowerCase().contains("book"));
	}
	
	//	Given that I want to search an very long query, which there is not post relevant
	//	When I input the query and submit it
	//	Then I see that no result shows up
	@Test
	public void testNoResult() {
		//	Input an long query, which don't have match result
		driver.findElement(By.id("query")).sendKeys("abcdefghijk&*$#2");
		
		//	Submit the query
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		
		//	A no results div should exit
		WebElement noResult = driver.findElement(By.cssSelector("div[class='noresults']"));
		assertTrue(noResult.isDisplayed());
	}
	
	//	After each test, quit driver, since driver cannot quit by itself
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
