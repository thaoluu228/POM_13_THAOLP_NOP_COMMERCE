package pageObject.liveGuRu;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUI.liveGuru.HomePageUI;

public class HomePageObject extends AbstractPages {
	private WebDriver driver;
	
	public HomePageObject (WebDriver _driver) {
		driver = _driver;
	}

	public LoginPageObject clickToMyAccount() {
		waitToElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK );
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return new LoginPageObject(driver);
	}

}
