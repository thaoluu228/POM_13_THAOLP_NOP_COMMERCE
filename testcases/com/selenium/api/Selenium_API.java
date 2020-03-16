package com.selenium.api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import java.util.Random;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;

public class Selenium_API {
	WebDriver driver;

	
  @BeforeTest
  public void beforeTest() {
	
  }
  
  @Test
  public void TC_01_RegisterToSystem() throws InterruptedException {
	  
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
