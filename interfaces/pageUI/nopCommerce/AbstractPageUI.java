package pageUI.nopCommerce;

public class AbstractPageUI {
	public static final String FOOTER_SEARCH_LINK = "//div[@class='footer-block customer-service']//a[text()='Search']";
	public static final String HOME_PAGE= "//div[@class='header-logo']//img";
	public static final String FOOTER_NEW_PRODUCT_LINK = "//a[contains(text(),'New products')]";
	public static final String FOOTER_MY_ACCOUNT_LINK = "//div[@class='footer']//a[text()='My account']";

	//1 locator
	public static final String DYNAMIC_FOOTER_LINK = "//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_HEADER_LINK = "//div[@class='header']//a[text()='%s']";
	public static final String DYNAMIC_HEADER_MENU_LINK = "//div[@class='header-menu']//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]"; 
	
	//textbox
	public static final String DYNAMIC_TEXTBOX = "//input[@id='%s']";
	
	//radio button
	public static final String DYNAMIC_RADIO = "//input[@id='%s']";
	
	//dropdown
	public static final String DYNAMIC_DROPDOWN_LIST = "//select[@name='%s']";
}
