package com.nopcommerce.login;

import org.testng.annotations.Test;
import commons.AbstractPages;

import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login_02_RegisterandLogin_AbstractPage_Extend extends AbstractPages {
	private WebDriver driver;
	private String email, password;
	
  @BeforeTest
  public void beforeTest() {
	  String osName = System.getProperty("os.name");
	  if (osName.toLowerCase().contains("mac")) {
		  //System.setProperty("webdriver.chrome.driver", "./resources/chromedriver 2");
		System.setProperty("webdriver.gecko.driver", "./resources/geckodriver 2");
	  } else {
		System.setProperty("webdriver.chrome.driver", ".//resources//chromedriver 2");
	}
	  //driver = new ChromeDriver();
  
	  driver = new FirefoxDriver();
	

	  openUrl(driver, "https://demo.nopcommerce.com/");
	
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	
	  email = "thaoluu" + randomNumber() + "@yopmail.com";
	  password = "310228";
  }
  
  @Test
  public void TC_01_RegisterToSystem() throws InterruptedException {
	  clickToElement(driver, "//a[@class='ico-register']");
	  Thread.sleep(2000);
	  clickToElement(driver, "//input[@id='gender-female']");
	  Thread.sleep(2000);
	  
	  sendkeyToElement(driver, "//input[@id='FirstName']", "Thao");
	  Thread.sleep(2000);
	  sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
	  sendkeyToElement(driver, "//input[@id='Email']", email);
	  
	  selectItemInHtmlDropdown(driver, "//select[@name='DateOfBirthDay']", "3");
	  selectItemInHtmlDropdown(driver, "//select[@name='DateOfBirthMonth']", "October");
	  selectItemInHtmlDropdown(driver, "//select[@name='DateOfBirthYear']", "1996");
	  
	  sendkeyToElement(driver, "//input[@id='Company']", "testing");
	  sendkeyToElement(driver, "//input[@id='Password']", password);
	  sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);
	  
	  clickToElement(driver, "//input[@id='register-button']");
	  
	  String resultText = getTextElement(driver, "//div[@class='result']");
	  Assert.assertEquals(resultText, "Your registration completed");
	  //Thread.sleep(2000);
	  clickToElement(driver, "//a[@class='ico-logout']");
	  //Thread.sleep(2000);
  }
  
  @Test
  public void TC_02_LoginToSystem() throws InterruptedException {
	  
	  clickToElement(driver, "//a[@class='ico-login']");
	  //Thread.sleep(1000);
	  sendkeyToElement(driver, "//input[@id='Email']", email);
	  sendkeyToElement(driver, "//input[@id='Password']", password);
	  
	  clickToElement(driver, "//input[@class='button-1 login-button']");
	  
	  Assert.assertTrue(isElementDisplayed(driver, "//a[@class='ico-account']"));
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
