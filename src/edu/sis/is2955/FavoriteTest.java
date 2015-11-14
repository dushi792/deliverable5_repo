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
 * As a user, I would like to favorite some posts. 
 * So that I can save it for later review.
 *
 */

public class FavoriteTest {
	
	private WebDriver driver;
	
	//	Start at an event page of craiglist for each test
	//	Since we think that this favorite fuction should be personal, it is public fuction, which means user doesn't need to login 
	//	So we think this fuction is a defect for this website
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://pittsburgh.craigslist.org/pet/5315898990.html");
	}
	
	
	//	Given that I see a favorite post
	//	When I click on the favorite star
	//	Then I see that favorite star change to gold and its class attribute change to "star fav"
	@Test
	public void testFavorite() {
		//	Get the star button, before click the button's class attribute should be "star"
		WebElement favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		favoriteStar.click();
		
		//	After click the button the calss value of it should be changed to "star fav"
		WebElement favoritedStar = driver.findElement(By.cssSelector("span[class='star fav']"));
		assertTrue(favoritedStar.isDisplayed());
	}
	
	//	Given that I have favorited a post
	//	When I click on the gold favorite star
	//	Then I see that favorite star change to grey and its class attribute change back to "star"
	@Test
	public void testUnfavorite() {
		//	Get the star button, before click the button's class attribute should be "star"
		WebElement favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		favoriteStar.click();
		
		//	After click the button the calss value of it should be changed to "star fav"
		WebElement favoritedStar = driver.findElement(By.cssSelector("span[class='star fav']"));
		assertTrue(favoritedStar.isDisplayed());
		favoritedStar.click();
		
		//	After click the button again, the calss value of it should be changed back to "star"
		assertTrue(favoriteStar.isDisplayed());
	}
	
	//	Given that I have favorited two posts
	//	When I click on the favorite manage button
	//	Then I see the titles of the favorited posts show on the page
	@Test
	public void testManageFavorite() {
		//	Get the star button, before click the button's class attribute should be "star"
		WebElement favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		favoriteStar.click();
		
		//	Change the driver to another post and favorite it
		driver.get("http://pittsburgh.craigslist.org/apa/5295065965.html");
		favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		favoriteStar.click();
		
		//	Get the favorate icon, click it to manage all the favorates
		WebElement favorite = driver.findElement(By.cssSelector("a[class='favlink']"));
		favorite.click();
		
		//	Check all the two favorited posts are showed up
		WebElement pet = driver.findElement(By.linkText("Aspen Dog Cage (up to 30lbs)"));
		WebElement apa = driver.findElement(By.linkText("Fabulous & Modern Two Bedroom with a Balcony Near Frick Park!"));
		assertTrue(pet.isDisplayed() && apa.isDisplayed());
	}
	
	//	Given that I have favorited two posts and at the favorite manage page
	//	When I click on the unfavorite all button
	//	Then I see total number of favorited posts change to 0 instantly
	@Test
	public void testReduceAllFavorites() {
		//	Get the star button, before click the button's class attribute should be "star"
		WebElement favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		favoriteStar.click();
		
		//	Change the driver to another post and favorite it
		driver.get("http://pittsburgh.craigslist.org/apa/5295065965.html");
		favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		favoriteStar.click();
		
		//	Get the favorate icon, click it to manage all the favorates
		WebElement favorite = driver.findElement(By.cssSelector("a[class='favlink']"));
		favorite.click();
		
		//	Get the total number of favorates before unfavorate, it should be 2
		WebElement favoriteNum = driver.findElement(By.cssSelector("span[class='n']"));
		assertEquals(favoriteNum.getText(),"2");
		
		//	Unfavorate all the posts, the number should be 0
		WebElement selectAll = driver.findElement(By.cssSelector("span[class='star v fav']"));
		selectAll.click();
		assertEquals(favoriteNum.getText(),"0");
	}
	
	//	After each test, quit driver, since driver cannot quit by itself
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
