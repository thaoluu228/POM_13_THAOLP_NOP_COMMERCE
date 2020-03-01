package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AbstractTest {
	private WebDriver driver;
	
	public WebDriver getBrowserDriver(String browserName, String autUrl) {
	
	  if (browserName.equalsIgnoreCase("chrome")) {
		  System.setProperty("webdriver.chrome.driver", "./resources/chromedriver 2");
		  driver = new ChromeDriver ();		
	} else { 
		  System.setProperty("webdriver.gecko.driver", "./resources/geckodriver 2");
		  driver = new FirefoxDriver ();	
	}
	  
	  driver.get(autUrl);
	
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  return driver;
	  
	}

}
	
