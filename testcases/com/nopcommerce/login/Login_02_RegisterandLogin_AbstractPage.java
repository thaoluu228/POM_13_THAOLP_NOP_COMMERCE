package com.nopcommerce.login;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login_02_RegisterandLogin_AbstractPage {
	private WebDriver driver;
	private String email, password;
	private AbstractPage abstractPage;
	
 
  @BeforeTest
  public void beforeTest() {
	  String osName = System.getProperty("os.name");
	  if (osName.toLowerCase().contains("mac")) {
		  System.setProperty("webdriver.chrome.driver", "./resources/chromedriver 2");
		
	} else {
		System.setProperty("webdriver.chrome.driver", ".//resources//chromedriver 2");
	}
	  driver = new ChromeDriver();
	  
	  abstractPage = new AbstractPage(driver);
	  abstractPage.openUrl("https://demo.nopcommerce.com/");
	
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	
	  email = "thaoluu" + randomNumber() + "@yopmail.com";
	  password = "310228";
  }
  
  @Test
  public void TC_01_RegisterToSystem() throws InterruptedException {
	  abstractPage.clickToElement("//a[@class='ico-register']");
	  Thread.sleep(2000);
	  abstractPage.clickToElement("//input[@id='gender-female']");
	  Thread.sleep(2000);
	  
	  abstractPage.sendkeyToElement("//input[@id='FirstName']", "Thao");
	  Thread.sleep(2000);
	  abstractPage.sendkeyToElement("//input[@id='LastName']", "Phuong");
	  abstractPage.sendkeyToElement("//input[@id='Email']", email);
	  
	  abstractPage.selectItemInHtmlDropdown("//select[@name='DateOfBirthDay']", "3");
	  abstractPage.selectItemInHtmlDropdown("//select[@name='DateOfBirthMonth']", "October");
	  abstractPage.selectItemInHtmlDropdown("//select[@name='DateOfBirthYear']", "1996");
	  
	  abstractPage.sendkeyToElement("//input[@id='Company']", "testing");
	  abstractPage.sendkeyToElement("//input[@id='Password']", password);
	  abstractPage.sendkeyToElement("//input[@id='ConfirmPassword']", password);
	  
	  abstractPage.clickToElement("//input[@id='register-button']");
	  
	  String resultText = abstractPage.getTextElement("//div[@class='result']");
	  Assert.assertEquals(resultText, "Your registration completed");
	  abstractPage.waitToElementClickable("//a[@class='ico-logout']");
	  abstractPage.clickToElement("//a[@class='ico-logout']");
  }
  
  @Test
  public void TC_02_LoginToSystem() throws InterruptedException {
	  abstractPage.clickToElement("//a[@class='ico-login']");
	  abstractPage.clickToElement("//a[@class='ico-login']");
	  abstractPage.sendkeyToElement("//input[@id='Email']", email);
	  abstractPage.sendkeyToElement("//input[@id='Password']", password);
	  
	  abstractPage.clickToElement("//input[@class='button-1 login-button']");
	  
	  Assert.assertTrue(abstractPage.isElementDisplayed("//a[@class='ico-account']"));
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
