package commons;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.Reporter;

public class AbstractTest {
	private WebDriver driver;
	protected final Log log;
	private String rootFolder = System.getProperty("user.dir");
	public AbstractTest() {
		log = LogFactory.getLog(getClass());
	}
	
	public WebDriver getBrowserDriver(String browserName, String autUrl) {
	
	  if (browserName.equalsIgnoreCase("chrome")) {
		  System.setProperty("webdriver.chrome.driver", "./resources/chromedriver");
		  //disable log
		  System.setProperty("webdriver.chrome.args", "--disable-logging");
		  System.setProperty("webdriver.chrome.silentOutput", "true");
		  
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("--incognito");
		  //install plugin
//		  File file = new File(rootFolder + "/Extension/google translate.crx");
//		  options.addExtensions(file);
		  
		  driver = new ChromeDriver (options);		
//		  driver = new ChromeDriver();
	} else { 
		  System.setProperty("webdriver.gecko.driver", "./resources/geckodriver 2");
		  //disable log
		  System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		  System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, rootFolder + "/LogBrowser/Firefox.log");
		  
		  FirefoxOptions options = new FirefoxOptions();
		  options.addArguments("-private");
		//install plugin
//		  FirefoxProfile profile = new FirefoxProfile();
//		  File firefoxEx = new File(rootFolder + "/Extension/google_translate.xpi");
//		  profile.addExtension(firefoxEx);
//		  options.setProfile(profile);
		  
		  driver = new FirefoxDriver (options);	
//		  driver = new FirefoxDriver();
	}
	  
	  driver.get(autUrl);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  return driver;
	  
	}

	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	
	public int randomNumber() {
		Random rand = new Random();
		int value = rand.nextInt(1000);
		return value;
		}
	
	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return day + "";
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return month + "";
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		return now.getYear() + "";
	}

	protected String getToday() {
		return getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDay();
	}

}
	
