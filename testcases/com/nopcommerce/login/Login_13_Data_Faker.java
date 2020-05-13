package com.nopcommerce.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.DataHelper;
import pageObjects.nopCommerce.HomePage;
import pageObjects.nopCommerce.LoginPage;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login_13_Data_Faker extends AbstractTest {
	private WebDriver driver;
	private String registerSuccessMsg;
	private HomePage homePage;
	private LoginPage loginPage;
	private RegisterPage registerPage;
	private DataHelper data;
	private String firstName, lastName, email, password, company;
	
  @Parameters({ "browser" , "url" })
  @BeforeTest
	public void beforeTest(String browserName, String autUrl ) {
			  
	  driver = getBrowserDriver(browserName, autUrl);
	
	  homePage = PageGeneratorManager.getHomePageObject(driver);
	  
	  data = DataHelper.getData();
	  firstName = data.getFirstName();
	  lastName = data.getLastName();
	  email = data.getEmail();
	  password = data.getPassword();
	  company = data.getCompany();
  }
  
  @Test
  public void TC_01_RegisterToSystem() throws InterruptedException {
	  homePage.openHeaderSearchByName(driver, "Register");
	  registerPage = PageGeneratorManager.getRegisterPageObject(driver);
	  registerPage.clickToRadioButtonID(driver, "gender-male");;
	  registerPage.inputToTextboxByID(driver, "FirstName", firstName);
	  registerPage.inputToTextboxByID(driver, "LastName", lastName);
	  
	  registerPage.selectDropdownByName(driver, "DateOfBirthDay", "3");
	  registerPage.selectDropdownByName(driver, "DateOfBirthMonth", "October");
	  registerPage.selectDropdownByName(driver, "DateOfBirthYear", "1996");
	  
	  registerPage.inputToTextboxByID(driver, "Email", email);
	  registerPage.inputToTextboxByID(driver, "Company", company);
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
