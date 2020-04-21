package com.nopcommerce.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.nopCommerce.DesktopPage;
import pageObjects.nopCommerce.HomePage;
import pageObjects.nopCommerce.PageGeneratorManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class Login_12_Sort_Asc_Desc extends AbstractTest {
	private WebDriver driver;
	private HomePage homePage;
	private DesktopPage desktopPage;
	
  @Parameters({ "browser" , "url" })
  @BeforeTest
	public void beforeTest(String browserName, String autUrl ) {
			  
	  driver = getBrowserDriver(browserName, autUrl);
	
	  homePage = PageGeneratorManager.getHomePageObject(driver);
	
	
  }
  
  @Test
  public void TC_01_Sort_Name() {
	  homePage.openHeaderMenuSearchByName(driver, "Computers", "Desktops");
	  desktopPage = PageGeneratorManager.getDesktopPage(driver);
	  
	  desktopPage.selectDropdownByName(driver, "products-orderby", "Name: A to Z");
	  
	  verifyTrue(desktopPage.isNameDataSortedAscendingNop(driver));
	  
	  desktopPage.selectDropdownByName(driver, "products-orderby", "Name: Z to A");
	  
	  verifyTrue(desktopPage.isNameDataSortedDescendingNop(driver));
  }
  
  @Test
  public void TC_02_Sort_Price () {
	 desktopPage.openHeaderMenuSearchByName(driver, "Computers", "Desktops");
	 
	 desktopPage.selectDropdownByName(driver, "products-orderby", "Price: Low to High");
	 
	 verifyTrue(desktopPage.isPriceDataSortedAscendingNop(driver));
	 
	  
  }
  
 

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  

}
