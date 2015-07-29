package edu.sis.is2955;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * As a user, I would like to log in Craiglist. 
 * So that I can do some personal operations.
 *
 */

public class LoginTest {
	private WebDriver driver;
	
	//  Start at the home page of craiglist for each test
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://pittsburgh.craigslist.org/");
	}
	
	//	Given that I have a right account and a right password
	//	When I log into the website
	//	Then I see that the logout button displays in the page.
	@Test
	public void testSuccessLogIn() throws Exception {
		//click my account button to the login page
		driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
		
		//input the account number as "dushi792@gmail.com", and correct password as "Dushi792"
		//This is a test account, is not our real account
		driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
		driver.findElement(By.id("inputPassword")).sendKeys("abc123456");
		
		//click submit button to login
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		
		//if login success, there should be a log out button on the top corner
		WebElement logoutButton = driver.findElement(By.linkText("log out"));
		assertTrue(logoutButton.getText().equals("log out"));
	}
	
	//	Given that I have a right account and a wrong password
	//	When I log into the website
	//	Then I see that the website provides an error information about this wrong password.
	@Test
	public void testFailLogIn() throws Exception {
		//click my account button to the login page
		driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
		
		//input the account number as "dushi792@gmail.com", and wrong password as "Dushi"
		driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
		driver.findElement(By.id("inputPassword")).sendKeys("abc1");
		
		//click submit button to login
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		
		//since the password is wrong, there should be an error message shows up
		WebElement failMessage = driver.findElement(By.cssSelector("p[class=\"error\"]"));
		assertTrue(failMessage.isDisplayed());
	}
	
	//	Given that I have a right account and a right password and already log into my account
	//	When I log out the website
	//	Then I see that the website title change back to login words
	@Test
	public void testLogOut() throws Exception {
		//click my account button to the login page
		driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
		
		//input the account number as "dushi792@gmail.com", and correct password as "Dushi792"
		driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
		driver.findElement(By.id("inputPassword")).sendKeys("abc123456");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		
		//click log out button to logout
		driver.findElement(By.linkText("log out")).click();
		
		//after log out, the title should change back to login
		String title = driver.getTitle();
		assertTrue(title.contains("craigslist: account log in"));
	}
	
	//  after each test, quit driver, since driver cannot quit by itself
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
