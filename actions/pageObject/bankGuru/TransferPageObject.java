package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;

public class TransferPageObject extends AbstractPages  {
	WebDriver driver;
	
	public TransferPageObject (WebDriver _driver) {
		driver = _driver;
	}
}
