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

public class SaveAndManageSearchTest {
	private WebDriver driver;
	private String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://pittsburgh.craigslist.org";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testSaveSearch() {
		try {
			driver.get(baseUrl + "/");
		    driver.findElement(By.cssSelector("a.swp > span.txt")).click();
		    driver.findElement(By.linkText("save search")).click();
		    driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
		    driver.findElement(By.id("inputPassword")).sendKeys("Dushi792");
		    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		    //driver.findElement(By.className("pickbutton")).click();
		    assertEquals("search was saved.", driver.findElement(By.id("alert")).getText());
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	@Test
	public void testTurnOnAlert() {
		try {
			driver.get(baseUrl + "/");
		    driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
		    driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
		    driver.findElement(By.id("inputPassword")).sendKeys("Dushi792");
		    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		    driver.findElement(By.linkText("searches")).click();
		    assertEquals("off", driver.findElement(By.cssSelector("label")).getText());
		    driver.findElement(By.name("subNotify")).click();
		    assertEquals("on", driver.findElement(By.cssSelector("label > b")).getText());
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	@Test
	public void testSortSavedSearches() {
		try {
			driver.get(baseUrl + "/");
		    driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
		    driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
		    driver.findElement(By.id("inputPassword")).sendKeys("Dushi792");
		    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		    driver.findElement(By.linkText("searches")).click();
		    driver.findElement(By.xpath("//table[@id='savedsearchlist']/thead/tr/th[5]")).click();
		    driver.findElement(By.xpath("//table[@id='savedsearchlist']/thead/tr/th[5]")).click();
		    int hits1 = Integer.parseInt(driver.findElement(By.id("2541120")).getText());
		    int hits2 = Integer.parseInt(driver.findElement(By.id("2649915")).getText());
		    assertTrue(hits1 > hits2);
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	@Test
	public void testDeleteSavedSearches() {
		try {
			driver.get(baseUrl + "/");
		    driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
		    driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
		    driver.findElement(By.id("inputPassword")).sendKeys("Dushi792");
		    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		    driver.findElement(By.linkText("searches")).click();
		    driver.findElement(By.xpath("//a[2]/button")).click();
		    assertEquals("saved search was deleted.", driver.findElement(By.id("alert")).getText());
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
