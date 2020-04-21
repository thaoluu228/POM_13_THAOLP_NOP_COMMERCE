package com.nopcommerce.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.nopCommerce.FooterMyAccountPage;
import pageObjects.nopCommerce.FooterNewProduct;
import pageObjects.nopCommerce.FooterSearch;
import pageObjects.nopCommerce.HomePage;
import pageObjects.nopCommerce.LoginPage;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login_07_RegisterandLogin_Dynamic_Locator extends AbstractTest {
	private WebDriver driver;
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
  public void TC_01_RegisterToSystem() throws InterruptedException {
	  //homePage: click to Register link -> Register page
	  registerPage = homePage.clickToRegisterLink();
	  Thread.sleep(1000);
	  //click female radio
	  registerPage.clickToFemale();
	  //input to First name
	  registerPage.inputToFirstName("Thao");
	  Thread.sleep(1000);
	  //input to Last name
	  registerPage.inputToLastName("Phuong");
	  //select item in Day
	  registerPage.selectDay("3");
	  //select item in Month
	  registerPage.selectMonth("October");
	  //select item in Year
	  registerPage.selectYear("1996");
	  //input to Email
	  registerPage.inputToEmailTextbox(email);
	  //input to Company
	  registerPage.inputToCompanyTextbox("Testing");
	  Thread.sleep(1000);
	  //input to Password
	  registerPage.inputToPassword(password);
	  //input to Confirm Password
	  registerPage.inputToConfirmPassword(password);
	  //click to Register button
	  registerPage.clickToRegisterButton();
	  //verify register success
	  registerSuccessMsg = registerPage.getRegisterSuccessMessage();
	  Assert.assertEquals(registerSuccessMsg, "Your registration completed");
	  //logout to System -> Home Page
	  homePage = registerPage.clickToLogoutLink();
  }
  
  @Test
  public void TC_02_LoginToSystem() throws InterruptedException {
	  //click Login link -> Login Page
	  loginPage = homePage.clickToLoginLink();
	  Thread.sleep(1000);
	  //input to Email 
	  loginPage.inputToEmailTextbox(email);
	  //input to Password
	  loginPage.inputToPassword(password);
	  //click to login button -> homepage
	  homePage = loginPage.clickToLoginButton();
	  //verify My account link display
	  Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	  
  }
  
  @Test
  public void TC_03_Dynamic_Less() {
	  //homepage -> my account (footer)
	  myAccountPage = (FooterMyAccountPage) homePage.openFooterSearchByName(driver, "My account");
	  //my account -> search
	  searchPage = (FooterSearch) myAccountPage.openFooterSearchByName(driver, "Search");
	  //search -> new product
	  newProductPage = (FooterNewProduct) searchPage.openFooterSearchByName(driver, "New products");
	  //new product -> homepage
	   homePage = newProductPage.openHomePage(driver);
	  //homepage -> search
	  searchPage = (FooterSearch) homePage.openFooterSearchByName(driver, "Search");
  }
  
  @Test
  public void TC_04_Dynamic_More() {
	  //homepage -> my account (footer)
	  homePage.openFootersSearchByName(driver, "My account");
	  myAccountPage = PageGeneratorManager.getFooterMyAccountPage(driver);
	  
	  //my account -> search
	  myAccountPage.openFootersSearchByName(driver, "Search");
	  searchPage = PageGeneratorManager.getFooterSearch(driver);
	  
	  //search -> new product
	  searchPage.openFootersSearchByName(driver, "New products");
	  newProductPage = PageGeneratorManager.getFooterNewProduct(driver);
	  
	  //new product -> homepage
	   homePage = newProductPage.openHomePage(driver);
	  //homepage -> search
	  searchPage = (FooterSearch) homePage.openFooterSearchByName(driver, "Search");
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
