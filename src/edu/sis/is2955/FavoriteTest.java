package edu.sis.is2955;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FavoriteTest {
	
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
//		driver.get("http://pittsburgh.craigslist.org/");
//		driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
//		driver.findElement(By.id("inputEmailHandle")).sendKeys("dushi792@gmail.com");
//		driver.findElement(By.id("inputPassword")).sendKeys("Dushi792");
//		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		driver.get("http://pittsburgh.craigslist.org/pet/5145048821.html");
	}

	@Test
	public void testFavorite() {
		WebElement favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		//before click the button the class should be "star"
		favoriteStar.click();
		WebElement favoritedStar = driver.findElement(By.cssSelector("span[class='star fav']"));
		//after click the button the calss value of it should be changed to "star fav"
		assertTrue(favoritedStar.isDisplayed());
	}
	
	@Test
	public void testUnfavorite() {
		WebElement favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		//before click the button the class should be "star"
		favoriteStar.click();
		
		WebElement favoritedStar = driver.findElement(By.cssSelector("span[class='star fav']"));
		//after click the button the calss value of it should be changed to "star fav"
		assertTrue(favoritedStar.isDisplayed());
		favoritedStar.click();
		
		//after click the button the calss value of it should be changed back to "star"
		assertTrue(favoriteStar.isDisplayed());
	}
	
	@Test
	public void testManageFavorite() {
		WebElement favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		favoriteStar.click();
		driver.get("http://pittsburgh.craigslist.org/apa/5145316159.html");
		favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		favoriteStar.click();
		WebElement favorite = driver.findElement(By.cssSelector("a[class='favlink']"));
		favorite.click();
		WebElement pet = driver.findElement(By.linkText("Saltwater aquarium LED lights"));
		WebElement apa = driver.findElement(By.linkText("Huge 1 bedroom. All utilities included"));
		assertTrue(pet.isDisplayed()&&apa.isDisplayed());
	}
	
	@Test
	public void testReduceAllFavorites() {
		WebElement favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		favoriteStar.click();
		driver.get("http://pittsburgh.craigslist.org/apa/5145316159.html");
		favoriteStar = driver.findElement(By.cssSelector("span[class='star']"));
		favoriteStar.click();
		WebElement favorite = driver.findElement(By.cssSelector("a[class='favlink']"));
		favorite.click();
		WebElement favoriteNum = driver.findElement(By.cssSelector("span[class='n']"));
		assertEquals(favoriteNum.getText(),"2");
		WebElement selectall = driver.findElement(By.cssSelector("span[class='star v fav']"));
		selectall.click();
		assertEquals(favoriteNum.getText(),"0");
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
