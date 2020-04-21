package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUI.bankGuru.HomePageUI;

public class HomePageObject extends AbstractPages  {
	WebDriver driver;
	
	public HomePageObject (WebDriver _driver) {
		driver = _driver;
	}

	public boolean isWelcomeMessageDisplayed() {
		waitToElementDisplayed(driver, HomePageUI.WELCOME_MSG);
		return isElementDisplayed(driver, HomePageUI.WELCOME_MSG);
	}
}
