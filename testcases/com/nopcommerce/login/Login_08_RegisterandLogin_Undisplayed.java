package com.nopcommerce.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.FooterMyAccountPage;
import pageObjects.nopCommerce.FooterNewProduct;
import pageObjects.nopCommerce.FooterSearch;
import pageObjects.nopCommerce.HomePage;
import pageObjects.nopCommerce.LoginPage;
import pageObjects.nopCommerce.RegisterPage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login_08_RegisterandLogin_Undisplayed extends AbstractTest {
	private WebDriver driver;
	private WebElement element;
	private String email, password, registerSuccessMsg;
	private HomePage homePage;
	private LoginPage loginPage;
	private RegisterPage registerPage;
	private FooterMyAccountPage myAccountPage;
	private FooterNewProduct newProductPage;
	private FooterSearch searchPage;
	
  @Parameters({ "browser" , "url" })
  @BeforeTest
   public void beforeTest(String browserName, String autUrl ) {
		  
		  driver = getBrowserDriver(browserName, autUrl);
	  
	  homePage = PageGeneratorManager.getHomePageObject(driver);
	
	  email = "thaoluu" + randomNumber() + "@yopmail.com";
	  password = "310228";
  }
  
  @Test 
  public void TC_01_CheckDisplayed() throws InterruptedException {
	  registerPage = homePage.clickToRegisterLink();
	  //Firstname textbox displayed
	  Assert.assertTrue(registerPage.isFirstnameTextboxDisplayed());
	  
	  //Lastname textbox displayed
	  Assert.assertTrue(registerPage.isElementDisplayed(driver, "//input[@id='LastName']"));
  }
  
  @Test
  public void TC_02_CheckUndisplayed() {
	  //Request verification token undisplayed (co trong DOM)
	  Assert.assertFalse(registerPage.isRequestUndisplayed());
  }  
  @Test
  public void TC_03_CheckUndisplayed_Not_In_Dom() {
	  //ko co trong DOM
	  Assert.assertFalse(registerPage.isRegisterUndisplayed());
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
