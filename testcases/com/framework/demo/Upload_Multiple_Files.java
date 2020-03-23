package com.framework.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Upload_Multiple_Files {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String seleniumFileName = "upload1.png";
	String appiumFileName = "upload2.png";
	String specflowFileName = "upload3.png";
	String[] fileName = {seleniumFileName, appiumFileName, specflowFileName};

	@BeforeClass
	public void beforeClass() {
	 System.setProperty("webdriver.gecko.driver", "./resources/geckodriver 2");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	}

	@Test
	public void TC_01_Sendkeys() throws Exception {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");

		// Upload 1 file
		uploadMultipleFiles(seleniumFileName);
		Thread.sleep(3000);

		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'" + seleniumFileName + "')]")).isDisplayed());

		driver.navigate().refresh();

		// Upload 2 file
		uploadMultipleFiles(specflowFileName, appiumFileName);
		Thread.sleep(3000);

		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'" + appiumFileName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'" + specflowFileName + "')]")).isDisplayed());

		driver.navigate().refresh();

		// Upload 3 file
//		uploadMultipleFiles(seleniumFileName, specflowFileName, appiumFileName);
//		Thread.sleep(3000);
		uploadMultipleFiles(fileName);

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'" + seleniumFileName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'" + appiumFileName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'" + specflowFileName + "')]")).isDisplayed());
			

	}

	public void uploadMultipleFiles(String... fileNames) {
		String filePath = System.getProperty("user.dir") + "/uploadFile/";
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}

		fullFileName = fullFileName.trim();
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(fullFileName);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}