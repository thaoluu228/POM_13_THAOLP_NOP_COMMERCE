package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;

public class WithdrawlPageObject extends AbstractPages  {
	WebDriver driver;
	
	public WithdrawlPageObject (WebDriver _driver) {
		driver = _driver;
	}
}
