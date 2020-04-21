package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUI.bankGuru.RegisterPageUI;

public class RegisterPageObject extends AbstractPages  {
	WebDriver driver;
	
	public RegisterPageObject (WebDriver _driver) {
		driver = _driver;
	}

	public void inputToEmailTextbox(String email) {
		waitToElementDisplayed(driver, RegisterPageUI.TEXTBOX_EMAIL);
		sendkeyToElement(driver, RegisterPageUI.TEXTBOX_EMAIL, email);
	}

	public void clickSubmitButton() {
		waitToElementDisplayed(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserID() {
		waitToElementDisplayed(driver, RegisterPageUI.USER_ID);
		return getTextElement(driver, RegisterPageUI.USER_ID);
	}

	public String getPassword() {
		waitToElementDisplayed(driver, RegisterPageUI.USER_PASSWORD);
		return getTextElement(driver, RegisterPageUI.USER_PASSWORD);
	}

	public LoginPageObject openLoginPage(String loginPageURL) {
		openUrl(driver, loginPageURL);
		return PageGeneratorManager.getLoginPage(driver);
	}
}
