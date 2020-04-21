package com.nopcommerce.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.nopCommerce.HomePage;
import pageObjects.nopCommerce.PageGeneratorManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login_09_RegisterandLogin_Assert_Verify_Log_Report extends AbstractTest {
	//private WebDriver driver;
	//private WebElement element;

	//private HomePage homePage;
	
	
  @Parameters({ "browser" , "url" })
  @BeforeTest
   public void beforeTest(String browserName, String autUrl ) {
		  
		  //driver = getBrowserDriver(browserName, autUrl);
	  
	  //homePage = PageGeneratorManager.getHomePageObject(driver);
	
	  
  }
  
  @Test 
  public void TC_01_Assert() {
	  
	 Assert.assertTrue(true);
	 
	System.out.println("TC01 - Step 01");
	//newCustomerPage = homePage.openNewCustomerPage(driver);
	
	log.info("TC01 - Step 02");
	Assert.assertTrue(true);
	
	log.info("TC01 - Step 03");
	Assert.assertTrue(false);
	
	log.info("TC01 - Step 04");
	Assert.assertTrue(true);
	
  }
  
  @Test 
  public void TC_02_Verify() {
	  log.info("Verify - TC02 - Step 01");
	  verifyTrue(false);
	//newCustomerPage = homePage.openNewCustomerPage(driver);
	  
	  log.info("Verify - TC02 - Step 02");
	  verifyTrue(true);
	  
	  
	  log.info("Verify - TC02 - Step 03");
	  verifyTrue(false);
	  
	  
	  log.info("TC02 - Step 04");
	  verifyTrue(true);
	
	
  }
  

  @AfterTest
  public void afterTest() {
	  //driver.quit();
  }
  
}
