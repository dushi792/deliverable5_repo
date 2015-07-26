package edu.sis.jun;

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
		driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
		WebDriverWait wait = new WebDriverWait(driver,120);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("inputEmailHandle")));
		driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
		driver.findElement(By.id("inputPassword")).sendKeys("Dushi792");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,120);
		wait2.until(ExpectedConditions.elementToBeClickable(By.linkText("log out")));
		driver.findElement(By.linkText("log out")).click();
		WebDriverWait wait3 = new WebDriverWait(driver,120);
		wait3.until(ExpectedConditions.elementToBeClickable(By.linkText("craigslist")));
		String title = driver.getTitle();
		assertTrue(title.contains("craigslist: account log in"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}