package com.framework.datatable;

import org.testng.annotations.Test;

import commons.AbstractPages;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Handle_Dynamic_DataTable_Grid extends AbstractPages {
	WebDriver driver;
	String locator, total;
	int index;
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", "./resources/geckodriver 2");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
  }
  
  public void TC_01_Click_To_Page() {
	  driver.get("https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
	  
	  goToPageByPageNumber("5");
	  Assert.assertTrue(isPageActiveByPageNumber("5"));
	  
	  goToPageByPageNumber("10");
	  Assert.assertTrue(isPageActiveByPageNumber("10"));
	  
	  goToPageByPageNumber("16");
	  Assert.assertTrue(isPageActiveByPageNumber("16"));
  }
  
  
  public void TC_02_Click_To_Icon_By_Country() throws InterruptedException {
	  driver.get("https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
	  //delete
	  clickToIconNameByCountryName("Afghanistan", "qgrd-remove-row-btn");
	  Thread.sleep(1000);
	  clickToIconNameByCountryName("Angola", "qgrd-remove-row-btn");
	  //edit
	  clickToIconNameByCountryName("Argentina", "qgrd-edit-row-btn");
  }
  
  
  public void TC_03_Get_Total_Value_By_Country() throws InterruptedException {
	  driver.get("https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
	  
	  total = getTotalValueByCountryName("Argentina");
	  System.out.println("Argentina = "+ total);
	  
	  total = getTotalValueByCountryName("Aruba");
	  System.out.println("Aruba =" + total);
  }
  
  
  public void TC_04_Input_To_Textbox() throws InterruptedException {
	  driver.get("https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
	  inputTextboxByRoundNumberAndColumnName("name", "1", "500");
	  inputTextboxByRoundNumberAndColumnName("company", "2", "700");
	  
  }
  
  @Test
  public void TC_05_Input_To_Textbox() throws InterruptedException {
	  driver.get("https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
	  inputToTextboxByColumnAndRound("Contact Person", "2", "500");
	  Thread.sleep(1000);
	  inputToTextboxByColumnAndRound("Company", "3", "700");
	  Thread.sleep(1000);
  }
  
  public void goToPageByPageNumber(String pageNumber) {
	  locator = "//a[@class='qgrd-pagination-page-link' and text()='%s']";
	  waitToElementClickable(driver, locator, pageNumber);
	  clickToElement(driver, locator, pageNumber);
  }
  
  public boolean isPageActiveByPageNumber(String pageNumber) {
	  locator = "//a[@class='qgrd-pagination-page-link active' and text()='%s']";
	  waitToElementDisplayed(driver, locator, pageNumber);
	  return isElementDisplayed(driver, locator, pageNumber);
  }
  
  public void clickToIconNameByCountryName (String countryName, String iconName) {
	  locator = "//td[@data-key='country' and text()='%s']"
	  		+ "/preceding-sibling::td[@class='qgrd-actions']/button[@class='%s']";
	  waitToElementClickable(driver, locator, countryName, iconName);
	  clickToElement(driver, locator, countryName, iconName);
	  
  }
  
  public String getTotalValueByCountryName (String countryName) {
	  locator = "//td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='total']";
	  waitToElementDisplayed(driver, locator, countryName);
	  return getTextElement(driver, locator, countryName);
  }
  
  public void inputTextboxByRoundNumberAndColumnName(String columnName, String roundNumber, String value) {
	  locator = "//input[@id='tblAppendGrid_%s_%s']";
	  waitToElementDisplayed(driver, locator, columnName, roundNumber);
	  sendkeyToElement(driver, locator, value, columnName, roundNumber);
  }
  
  public void inputToTextboxByColumnAndRound(String columnName, String roundNumber, String value) {
	  locator = "//th[text()='%s']/preceding-sibling::th";
	  index = findElementsByXpath(driver, locator, columnName).size() + 1;
	  locator = "//tr[" + roundNumber + "]//td["+ index +"]/input";
	  waitToElementDisplayed(driver, locator);
	  sendkeyToElement(driver, locator, value);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
