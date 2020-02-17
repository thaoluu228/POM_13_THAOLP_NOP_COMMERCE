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
	  
  }
  
  @Test
  public void TC_03_LoginIncorrectEmail() {
	  
  }
  
  @Test
  public void TC_04_LoginWithPasswordLessThan6Chars() {
	  
  }
  

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
