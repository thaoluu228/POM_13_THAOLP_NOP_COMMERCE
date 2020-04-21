package pageUI.bankGuru;

public class AbstractBackGuruPageUI {
	public static final String DYNAMIC_MENU_LINK = "//ul[@class='menusubnav']//a[text()='%s']";
	
	public static final String DYNAMIC_TEXTBOX = "//input[@name='%s']";
	
	public static final String DYNAMIC_TEXTAREA = "//textarea[@name='%s']";
	
	public static final String DYNAMIC_RADIO = "//input[@value='%s']";
	
	public static final String DYNAMIC_DROPDOWN = "//select[@name='%s']";
	
	public static final String DYNAMIC_BUTTON = "//input[@value='%s']";
	
	public static final String HEADING = "//p[@class='heading3']";
	
	public static final String DYNAMIC_ROW_NAME = "//td[text()='%s']/following-sibling::td";

}
