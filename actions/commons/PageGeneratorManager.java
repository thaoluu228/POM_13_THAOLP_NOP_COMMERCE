package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.nopCommerce.FooterMyAccountPage;
import pageObjects.nopCommerce.FooterNewProduct;
import pageObjects.nopCommerce.FooterSearch;
import pageObjects.nopCommerce.HomePage;
import pageObjects.nopCommerce.LoginPage;
import pageObjects.nopCommerce.RegisterPage;

public class PageGeneratorManager {
	
	//cap phat viec khoi tao homepage
	public static HomePage getHomePageObject (WebDriver driver) {
		return new HomePage(driver);
	}
	//khoi tao register page
	public static RegisterPage getRegisterPageObject(WebDriver driver) {
		return new RegisterPage(driver);
	}
	//khoi tao login page
	public static LoginPage getLoginPageObject (WebDriver driver) {
		return new LoginPage(driver);
	}
	
	public static FooterMyAccountPage getFooterMyAccountPage (WebDriver driver) {
		return new FooterMyAccountPage(driver);
	}
	
	public static FooterNewProduct getFooterNewProduct (WebDriver driver) {
		return new FooterNewProduct(driver);
	}
	
	public static FooterSearch getFooterSearch (WebDriver driver) {
		return new FooterSearch(driver);
	}

}
