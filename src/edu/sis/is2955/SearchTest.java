package edu.sis.is2955;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchTest {
	
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://pittsburgh.craigslist.org/search/sss");
	}

	@Test
	public void testEnglishQuery() {
		driver.findElement(By.id("query")).sendKeys("book");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		WebElement Firstresult = driver.findElement(By.xpath("/html/body/section/div[2]/form/div[2]/div[3]/p[1]/span/span[2]/a"));
		assertTrue(Firstresult.getText().toLowerCase().contains("book"));
	}
	
	@Test
	public void testChineseQuery() {
		driver.findElement(By.id("query")).sendKeys("书");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		WebElement Firstresult = driver.findElement(By.xpath("/html/body/section/div[2]/form/div[2]/div[3]/p[1]/span/span[2]/a"));
		assertTrue(Firstresult.isDisplayed()&&!Firstresult.getText().contains("书"));
	}
	
	@Test
	public void testEngChinQuery() {
		driver.findElement(By.id("query")).sendKeys("书book");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		WebElement Firstresult = driver.findElement(By.xpath("/html/body/section/div[2]/form/div[2]/div[3]/p[1]/span/span[2]/a"));
		assertTrue(Firstresult.isDisplayed()&&!Firstresult.getText().contains("书"));
		assertTrue(Firstresult.isDisplayed()&&Firstresult.getText().toLowerCase().contains("book"));
	}
	
	@Test
	public void testNoResult() {
		driver.findElement(By.id("query")).sendKeys("abcdefghijk&*$#2");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		WebElement NoResult = driver.findElement(By.cssSelector("div[class='noresults']"));
		assertTrue(NoResult.isDisplayed());
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
