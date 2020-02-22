package com.liveguru.login;

import org.testng.annotations.Test;

import pageObject.liveGuRu.HomePageObject;
import pageObject.liveGuRu.LoginPageObject;
import pageObject.liveGuRu.MyDashboardPage;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class Login_01_LoginToSystem {
	private WebDriver driver;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private MyDashboardPage dashboardPage;
	
  @BeforeClass
  public void beforeClass() {
	  String osName = System.getProperty("os.name");
	  if (osName.toLowerCase().contains("mac")) {
		  System.setProperty("webdriver.chrome.driver", "./resources/chromedriver 2");
//		System.setProperty("webdriver.gecko.driver", "./resources/geckodriver 2");
	  } else {
		System.setProperty("webdriver.chrome.driver", ".//resources//chromedriver 2");
	}
	  driver = new ChromeDriver();
  
//	  driver = new FirefoxDriver();
	  driver.get("http://live.demoguru99.com/index.php/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  
	  homePage = new HomePageObject(driver);
  }
  
  @Test
  public void TC_01_LoginWithEmptyEmailAndPassword() {
	 //click to my account -> login page
	  loginPage = homePage.clickToMyAccount();
	 //input empty Email
	  loginPage.inputToEmailTextBox("");
	  //input empty password
	  loginPage.inputToPassword("");
	  //click Login button
	  loginPage.clickToLoginButton();
	  //error msg
	  Assert.assertTrue(loginPage.isEmailMsgErrorDisplayed("This is a required field."));
	  //error pw
	  Assert.assertTrue(loginPage.isPasswordMsgErrorDisplayed("This is a required field."));
  }
  
  @Test
  public void TC_02_LoginInvalidEmail() {
	  loginPage = homePage.clickToMyAccount();
	  
	  loginPage.inputToEmailTextBox("thaoluu@y123.123");
	  
	  loginPage.inputToPassword("");
	  
	  loginPage.clickToLoginButton();
	  
	  Assert.assertTrue(loginPage.isInvalidEmailMsgErrorDisplayed("Please enter a valid email address. For example johndoe@domain.com."));
  }
  
  @Test
  public void TC_03_LoginIncorrectEmail() {
      loginPage = homePage.clickToMyAccount();
	  
	  loginPage.inputToEmailTextBox("thaoluu1@yopmail.com");
	  
	  loginPage.inputToPassword("123456");
	  
	  loginPage.clickToLoginButton();
	  
	  Assert.assertTrue(loginPage.isIncorrectdEmailMsgErrorDisplayed("Invalid login or password."));
	  
  }
  
  @Test
  public void TC_04_LoginWithPasswordLessThan6Chars() throws InterruptedException {
      loginPage = homePage.clickToMyAccount();
	  
	  loginPage.inputToEmailTextBox("thaoluu1@yopmail.com");
	  
	  loginPage.inputToPassword("12345");
	  
	  loginPage.clickToLoginButton();
	  Thread.sleep(1000);
	  
	  Assert.assertTrue(loginPage.isLessThan6PassMsgErrorDisplayed("Please enter 6 or more characters without leading or trailing spaces."));
  }
  
  @Test
  public void TC_05_LoginCorrectEmailAndPassword() {
      loginPage = homePage.clickToMyAccount();
	  
	  loginPage.inputToEmailTextBox("thaoluu@yopmail.com");
	  
	  loginPage.inputToPassword("123456");
	  //click to login -> my dashboard
	  dashboardPage = loginPage.clickToLoginButton();
	  
	  Assert.assertTrue(dashboardPage.isFullnameOrEmailDisplayed("thao luu phuong"));
	  //Assert.assertTrue(dashboardPage.isFullnameOrEmailDisplayed("thaoluu@yopmail.com"));
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
