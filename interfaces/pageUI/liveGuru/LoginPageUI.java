package pageUI.liveGuru;

public class LoginPageUI {
	public static final String EMAIL_TEXTBOX = "//input[@id='email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='pass']";
	public static final String LOGIN_BUTTON = "//button[@id='send2']";
	public static final String ERROR_MSG_EMPTY_EMAIL = "//div[@id='advice-required-entry-email' and text()='This is a required field.']";
	public static final String ERROR_MSG_EMPTY_PASSWORD = "//div[@id='advice-required-entry-pass' and text()='This is a required field.']";
}
