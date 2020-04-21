package pageObject.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;

public class NewCustomerPageObject extends AbstractPages  {
	WebDriver driver;
	
	public NewCustomerPageObject (WebDriver _driver) {
		driver = _driver;
	}
}
