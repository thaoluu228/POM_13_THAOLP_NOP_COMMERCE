package pageUI.liveGuru;

public class LoginPageUI {
	public static final String EMAIL_TEXTBOX = "//input[@id='email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='pass']";
	public static final String LOGIN_BUTTON = "//button[@id='send2']";
	public static final String ERROR_MSG_EMPTY_EMAIL = "//div[@id='advice-required-entry-email' and text()='%s']";
	public static final String ERROR_MSG_EMPTY_PASSWORD = "//div[@id='advice-required-entry-pass' and text()='%s']";
	public static final String ERROR_MSG_INVALID_EMAIL = "//div[@id='advice-validate-email-email' and text()='%s']";
	public static final String ERROR_MSG_INCORRECT_EMAIL = "//span[text()='%s']";
	public static final String ERROR_MSG_LESSTHAN_PASS = "//div[@id='advice-validate-password-pass' and text()='%s']";
	
}
