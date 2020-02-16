package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUI.LoginPageUI;

public class LoginPage extends AbstractPages  {
	private WebDriver driver;
	
	public LoginPage (WebDriver _driver) {
		driver = _driver;
	}

	public void inputToEmailTextbox(String emailValue) {
		waitToElementDisplayed(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputToPassword(String password) {
		waitToElementDisplayed(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public HomePage clickToLoginButton() {
		waitToElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return new HomePage(driver);
		
	}

}
