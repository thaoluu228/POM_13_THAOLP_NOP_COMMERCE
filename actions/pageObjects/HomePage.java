package pageObjects;


import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUI.HomePageUI;

public class HomePage extends AbstractPages {
	private WebDriver driver;
	
	public HomePage (WebDriver _driver) {
		driver = _driver;
	}
	

	public RegisterPage clickToRegisterLink() {
		waitToElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return new RegisterPage(driver);
	}

	public LoginPage clickToLoginLink() {
		waitToElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return new LoginPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitToElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	

}
