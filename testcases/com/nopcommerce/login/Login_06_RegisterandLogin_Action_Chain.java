package com.nopcommerce.login;

import org.testng.annotations.Test;

import pageObjects.nopCommerce.FooterMyAccountPage;
import pageObjects.nopCommerce.FooterNewProduct;
import pageObjects.nopCommerce.FooterSearch;
import pageObjects.nopCommerce.HomePage;
import pageObjects.nopCommerce.LoginPage;
import pageObjects.nopCommerce.RegisterPage;

import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login_06_RegisterandLogin_Action_Chain {
	private WebDriver driver;
	private String email, password, registerSuccessMsg;
	private HomePage homePage;
	private LoginPage loginPage;
	private RegisterPage registerPage;
	private FooterMyAccountPage myAccountPage;
	private FooterNewProduct newProductPage;
	private FooterSearch searchPage;
	
  @BeforeTest
  public void beforeTest() {
	  String osName = System.getProperty("os.name");
	  if (osName.toLowerCase().contains("mac")) {
//		  System.setProperty("webdriver.chrome.driver", "./resources/chromedriver 2");
		System.setProperty("webdriver.gecko.driver", "./resources/geckodriver 2");
	  } else {
		System.setProperty("webdriver.chrome.driver", ".//resources//chromedriver 2");
	}
//	  driver = new ChromeDriver();
	  
  
	  driver = new FirefoxDriver();
	  
	  driver.get("https://demo.nopcommerce.com/");
	
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  homePage = new HomePage(driver);
	
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
  public void TC_03_ActionChain() {
	  //homepage -> my account (footer)
	  myAccountPage = homePage.openMyAccountPage(driver);
	  //my account -> search
	  searchPage = myAccountPage.openFooterSearchPage(driver);
	  //search -> new product
	  newProductPage = searchPage.openNewProductPage(driver);
	  //new product -> homepage
	   homePage = newProductPage.openHomePage(driver);
	  //homepage -> search
	  searchPage = homePage.openFooterSearchPage(driver);
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
