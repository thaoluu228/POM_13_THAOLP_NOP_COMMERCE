package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUI.bankGuru.LoginPageUI;

public class LoginPageObject extends AbstractPages  {
	WebDriver driver;
	
	public LoginPageObject (WebDriver _driver) {
		driver = _driver;
	}

	public String getLoginPageURL() {
		return getCurrentPageUrl(driver);
	}

	public RegisterPageObject clickToHereLink() {
		waitToElementDisplayed(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public void inputToUserIDTextbox(String userID) {
		waitToElementClickable(driver, LoginPageUI.USERID_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERID_TEXTBOX, userID);
		
	}

	public void inputToPassword(String password) {
		waitToElementClickable(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public HomePageObject clickToLoginButton() {
		waitToElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}
}
