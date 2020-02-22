package pageObject.liveGuRu;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUI.liveGuru.MyDashboardUI;

public class MyDashboardPage extends AbstractPages {
	private WebDriver driver;
	public MyDashboardPage (WebDriver driver) {
		this.driver = driver;
	}
	

	public boolean isFullnameOrEmailDisplayed(String value) {
		
		return isElementDisplayed(driver, String.format(MyDashboardUI.EMAIL_PASSWORD_TEXT, value));
	}

	

}
