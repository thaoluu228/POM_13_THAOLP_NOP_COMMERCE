package com.nopcommerce.login;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_RegisterToSystem;

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

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login_10_Register_ShareClassState extends AbstractTest {
	private WebDriver driver;
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
	
	  
	  //click Login link -> Login Page
	  loginPage = homePage.clickToLoginLink();
	  //input to Email 
	  loginPage.inputToEmailTextbox(Common_01_RegisterToSystem.email);
	  //input to Password
	  loginPage.inputToPassword(Common_01_RegisterToSystem.password);
	  //click to login button -> homepage
	  homePage = loginPage.clickToLoginButton();
	  //verify My account link display
	  Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
  }
  
 
  
  @Test
  public void TC_01_View_Products(){
	  
	  
  }
  
  @Test
  public void TC_02_Order() {
	  
  }
  
  @Test
  public void TC_03_Payment() {
	  
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
 
}
