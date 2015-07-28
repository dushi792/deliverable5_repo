package edu.sis.is2955;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class AccountTest {
	private WebDriver driver;
	
	// Start at the log in page for dealmoon for each test
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://pittsburgh.craigslist.org/");
	}

	@Test
	public void testLogInLogOut() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
		driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
		driver.findElement(By.id("inputPassword")).sendKeys("Dushi792");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		driver.findElement(By.linkText("log out")).click();
		String title = driver.getTitle();
		assertTrue(title.contains("craigslist: account log in"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}