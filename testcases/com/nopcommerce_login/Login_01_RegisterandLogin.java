package com.nopcommerce_login;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login_01_RegisterandLogin {
	private WebDriver driver;
	
	private Select select;
	private String email, password;
	
 
  @BeforeTest
  public void beforeTest() {
    String projectPath = System.getProperty("user.dir");
	System.setProperty("webdriver.chrome.driver", projectPath + "/resources/chromedriver 2");
	driver = new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("https://demo.nopcommerce.com/");
	
	email = "thaoluu" + randomNumber() + "@yopmail.com";
	password = "310228";
  }
  
  @Test
  public void TC_01_RegisterToSystem() throws InterruptedException {
	  driver.findElement(By.cssSelector(".ico-register")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("#gender-female")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("#FirstName")).sendKeys("Thao");
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("#LastName")).sendKeys("Phuong");
	  driver.findElement(By.cssSelector("#Email")).sendKeys(email);
	  
	  select = new Select (driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
	  select.selectByVisibleText("3");
	  
	  select = new Select (driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
	  select.selectByVisibleText("October");
	  
	  select = new Select (driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
	  select.selectByVisibleText("1996");
			  
	  driver.findElement(By.cssSelector("#Password")).sendKeys(password);
	  driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys(password);
	  driver.findElement(By.cssSelector("#Company")).sendKeys("testing");
	  
	  driver.findElement(By.cssSelector("#register-button")).click();
	  
	  String resultText = driver.findElement(By.cssSelector(".result")).getText();
	  Assert.assertEquals(resultText, "Your registration completed");
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector(".ico-logout")).click();
	 
  }
  
  @Test
  public void TC_02_LoginToSystem() throws InterruptedException {
	  
	  driver.findElement(By.cssSelector(".ico-login")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("#Email")).sendKeys(email);
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("#Password")).sendKeys(password);
	  
	  driver.findElement(By.cssSelector(".login-button")).click();
	  
	  Assert.assertTrue(driver.findElement(By.cssSelector(".ico-account")).isDisplayed());
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
  public int randomNumber() {
		Random rand = new Random();
		int value = rand.nextInt(1000);
		return value;
		}

}
