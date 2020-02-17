package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUI.nopCommerce.RegisterPageUI;

public class RegisterPage extends AbstractPages {
	private WebDriver driver;
	
	public RegisterPage (WebDriver _driver) {
		driver = _driver;
	}

	public void clickToFemale() {
		waitToElementClickable(driver, RegisterPageUI.GENDER_FEMALE_RADIO);
		clickToElement(driver, RegisterPageUI.GENDER_FEMALE_RADIO);
	}

	public void inputToFirstName(String firstNameValue) {
		waitToElementDisplayed(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstNameValue);
		
	}

	public void inputToLastName(String lastNameValue) {
		waitToElementDisplayed(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastNameValue);
	}

	public void selectDay(String expectedValue) {
		waitToElementDisplayed(driver, RegisterPageUI.DAY_DROPDOWN);
		selectItemInHtmlDropdown(driver, RegisterPageUI.DAY_DROPDOWN, expectedValue);
		
	}

	public void selectMonth(String expectedValue) {
		waitToElementDisplayed(driver, RegisterPageUI.MONTH_DROPDOWN);
		selectItemInHtmlDropdown(driver, RegisterPageUI.MONTH_DROPDOWN, expectedValue);
		
	}

	public void selectYear(String expectedValue) {
		waitToElementDisplayed(driver, RegisterPageUI.YEAR_DROPDOWN);
		selectItemInHtmlDropdown(driver, RegisterPageUI.YEAR_DROPDOWN, expectedValue);
		
	}

	public void inputToEmailTextbox(String emailValue) {
		waitToElementDisplayed(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailValue);
		
	}

	public void inputToCompanyTextbox(String companyName) {
		waitToElementDisplayed(driver, RegisterPageUI.COMPANY_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.COMPANY_TEXTBOX, companyName);
		
	}

	public void inputToPassword(String password) {
		waitToElementDisplayed(driver, RegisterPageUI.PASSWORD);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD, password);
		
	}

	public void inputToConfirmPassword(String confirmPassword) {
		waitToElementDisplayed(driver, RegisterPageUI.CONFIRM_PASSWORD);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD, confirmPassword);
		
	}

	public void clickToRegisterButton() {
		waitToElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
		
	}

	public String getRegisterSuccessMessage() {
		waitToElementDisplayed(driver, RegisterPageUI.REGISTER_SUCCESS_TEXT);
		return getTextElement(driver, RegisterPageUI.REGISTER_SUCCESS_TEXT);
	}

	public HomePage clickToLogoutLink() {
		waitToElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
		return new HomePage(driver);
	}

}
