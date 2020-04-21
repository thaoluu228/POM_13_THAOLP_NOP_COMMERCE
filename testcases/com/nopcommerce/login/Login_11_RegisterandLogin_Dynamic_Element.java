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
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login_11_RegisterandLogin_Dynamic_Element extends AbstractTest {
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
	  homePage.openHeaderSearchByName(driver, "Register");
	  registerPage = PageGeneratorManager.getRegisterPageObject(driver);
	  registerPage.clickToRadioButtonID(driver, "gender-male");;
	  registerPage.inputToTextboxByID(driver, "FirstName", "Thao");
	  registerPage.inputToTextboxByID(driver, "LastName", "Luu");
	  
	  registerPage.selectDropdownByName(driver, "DateOfBirthDay", "3");
	  registerPage.selectDropdownByName(driver, "DateOfBirthMonth", "October");
	  registerPage.selectDropdownByName(driver, "DateOfBirthYear", "1996");
	  
	  registerPage.inputToTextboxByID(driver, "Email", email);
	  registerPage.inputToTextboxByID(driver, "Company", "Testing");
	  registerPage.inputToTextboxByID(driver, "Password", password);
	  registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);
	  registerPage.clickToRegisterButton();
	  registerSuccessMsg = registerPage.getRegisterSuccessMessage();
	  Assert.assertEquals(registerSuccessMsg, "Your registration completed");
	  registerPage.openHeaderSearchByName(driver, "Log out");
	  homePage = PageGeneratorManager.getHomePageObject(driver);
  }
  
  @Test
  public void TC_02_LoginToSystem() throws InterruptedException {
	  homePage.openHeaderSearchByName(driver, "Log in");
	  loginPage = PageGeneratorManager.getLoginPageObject(driver);
	  loginPage.inputToTextboxByID(driver, "Email", email);
	  loginPage.inputToTextboxByID(driver, "Password", password);
	  homePage = loginPage.clickToLoginButton();
	  Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	  
  }
  
 

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  

}
