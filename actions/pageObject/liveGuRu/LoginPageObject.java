package pageObject.liveGuRu;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUI.liveGuru.LoginPageUI;

public class LoginPageObject extends AbstractPages {
	private WebDriver driver;
	public LoginPageObject (WebDriver driver) {
		this.driver = driver;
	}
	public void inputToEmailTextBox(String valueEmail) {
		waitToElementDisplayed(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, valueEmail);
	}
	public void inputToPassword(String valuePassword) {
		waitToElementDisplayed(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, valuePassword);
	}
	public MyDashboardPage clickToLoginButton() {
		waitToElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return new MyDashboardPage(driver);
		//clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
	public boolean isEmailMsgErrorDisplayed(String errorMsg) {
		waitToElementDisplayed(driver, String.format(LoginPageUI.ERROR_MSG_EMPTY_EMAIL, errorMsg));
		return isElementDisplayed(driver, String.format(LoginPageUI.ERROR_MSG_EMPTY_EMAIL, errorMsg));
	}
	public boolean isPasswordMsgErrorDisplayed(String errorMsg) {
		waitToElementDisplayed(driver, String.format(LoginPageUI.ERROR_MSG_EMPTY_PASSWORD, errorMsg));
		return isElementDisplayed(driver, String.format(LoginPageUI.ERROR_MSG_EMPTY_PASSWORD, errorMsg));
	}
	public boolean isInvalidEmailMsgErrorDisplayed(String errorMsg) {
		waitToElementDisplayed(driver, String.format(LoginPageUI.ERROR_MSG_INVALID_EMAIL, errorMsg));
		return isElementDisplayed(driver, String.format(LoginPageUI.ERROR_MSG_INVALID_EMAIL, errorMsg));
	}
	public boolean isIncorrectdEmailMsgErrorDisplayed(String errorMsg) {
		waitToElementDisplayed(driver, String.format(LoginPageUI.ERROR_MSG_INCORRECT_EMAIL, errorMsg));
		return isElementDisplayed(driver, String.format(LoginPageUI.ERROR_MSG_INCORRECT_EMAIL, errorMsg));
	}
	public boolean isLessThan6PassMsgErrorDisplayed(String errorMsg) {
		waitToElementDisplayed(driver, String.format(LoginPageUI.ERROR_MSG_LESSTHAN_PASS, errorMsg));
		return isElementDisplayed(driver, String.format(LoginPageUI.ERROR_MSG_LESSTHAN_PASS, errorMsg));
	}

}
