package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.FooterMyAccountPage;
import pageObjects.nopCommerce.FooterNewProduct;
import pageObjects.nopCommerce.FooterSearch;
import pageObjects.nopCommerce.HomePage;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageUI.bankGuru.AbstractBackGuruPageUI;
import pageUI.nopCommerce.AbstractNopCommercePageUI;


public class AbstractPages {
	private Select select;
	private Actions action;
	private WebElement element;
	private WebDriverWait waitExplicit;
	private By byXpath;
	private Date date;
	private JavascriptExecutor jsExecutor;
	
	//Mo ra 1 url tu ben ngoai
	public void openUrl (WebDriver driver, String url) {
		driver.get(url);
		driver.manage().window().maximize();
		
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String...values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}
	
	public String getPageTitle (WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentPageUrl (WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void back (WebDriver driver) {
		driver.navigate().back();
	}
	
	public void refreshPage (WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void acceptAlert (WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert (WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert (WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	public String getTextElement (WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText();
	}
	
	public String getTextElement (WebDriver driver, String locator, String...values) {
		locator = String.format(locator, (Object[])values);
		return findElementByXpath(driver, locator).getText();
	}
	
	public void sendkeyToAlert (WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}
	
	public WebElement findElementByXpath (WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public WebElement findElementByXpath (WebDriver driver, String locator, String...values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElement(By.xpath(locator));
	}
	
	public List<WebElement> findElementsByXpath (WebDriver driver, String locator, String...values) {
		locator = String.format(locator, (Object[])values);
		return driver.findElements(By.xpath(locator));
	}
	
	public List<WebElement> findElementsByXpath (WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator));
	}
	
	public void checkTheCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckTheCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementUnDisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = findElementsByXpath(driver, locator);
		if (elements.size() == 0) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is in DOM but not Displayed");
			return true;
		} else {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Elemenet is Displayed");
			return false;
		}
	}
	
	public boolean isElementUnDisplayed(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = findElementsByXpath(driver, locator, values);
		if (elements.size() == 0) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is in DOM but not Displayed");
			return true;
		} else {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Elemenet is Displayed");
			return false;
		}
	}
	
	public void clickToElement (WebDriver driver, String locator) {
		findElementByXpath(driver, locator).click();
	}
	
	public By byXpathLocator (String locator) {
		return By.xpath(locator);
	}
	
	public By byXpathLocator (String locator, String...values) {
		locator = String.format(locator, (Object[])values);
		return By.xpath(locator);
	}
	
	public void clickToElement (WebDriver driver, String locator, String...values){
		findElementByXpath(driver,locator, values).click();
	}
	
	public void sendkeyToElement (WebDriver driver, String locator, String value) {
		findElementByXpath(driver, locator).clear();
		findElementByXpath(driver, locator).sendKeys(value);
	}
	
	public void sendkeyToElement (WebDriver driver, String locator, String value, String...values) {
		findElementByXpath(driver, locator, values).clear();
		findElementByXpath(driver, locator, values).sendKeys(value);
	}
	
	public int countElementNumber (WebDriver driver, String locator) {
		return findElementsByXpath(driver, locator).size();
	}
	
	public String getAttributeElement (WebDriver driver, String locator, String value) {
		return findElementByXpath(driver, locator).getAttribute(value);
	}
	
	public boolean isElementDisplayed (WebDriver driver, String locator) {
		try {
			  element = findElementByXpath(driver, locator);
			  return element.isDisplayed();	  
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean isElementDisplayed (WebDriver driver, String locator, String...values) {
		try {
			  element = findElementByXpath(driver, locator, values);
			  return element.isDisplayed();	  
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
    public boolean isElementSelected(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			element = findElementByXpath(driver, locator);
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return element.isEnabled();

		} catch (Exception ex) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator, String... values) {
		try {
			element = findElementByXpath(driver, locator, values);
			return element.isEnabled();

		} catch (Exception ex) {
			return false;
		}
	}

	public void hoverMouseToElement (WebDriver driver, String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.moveToElement(element).perform();
	}
	
	public void hoverMouseToElement (WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator, values);
		action.moveToElement(element).perform();
	}
	
	public void selectItemInHtmlDropdown (WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		select.selectByVisibleText(value);
	}
	
	public void selectItemInHtmlDropdown (WebDriver driver, String locator, String valueItem, String... values) {
		element = findElementByXpath(driver, locator, values);
		select = new Select(element);
		select.selectByVisibleText(valueItem);
	}
	
	public WebElement getSelectedItemInHtmlDropdown (WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		return select.getFirstSelectedOption();
	}
	
	public void selectIteminCustomDropdown(WebDriver driver, String parentXpath, String allItemsXpath, String expectedText) {
		findElementByXpath(driver, parentXpath).click();

		List<WebElement> allItems = findElementsByXpath(driver, allItemsXpath);

		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		for (WebElement item : allItems) {
			System.out.println("Text =" + item.getText());
			if (item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}
	}
	
	public void inputIteminCustomDropdown(WebDriver driver, String parentXpath, String inputXpath, String expectedText) {
		findElementByXpath(driver, parentXpath).click();
		findElementByXpath(driver, inputXpath).sendKeys(expectedText);
		action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath(inputXpath)), Keys.ENTER).perform();
	}

	public void sendKeyboardToElement (WebDriver driver, String locator, Keys key) {
		element = findElementByXpath(driver, locator);
		action = new Actions(driver);
		action.sendKeys(element, key).perform();
	}
	
	public void upload1FileBySendkey (WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.sendKeys(value);
		element.click();
	}
	
	public void uploadMutiFileBySendkey (WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.sendKeys(value);
		List <WebElement> startButton = findElementsByXpath(driver, locator);
		for (WebElement button: startButton ) {
			button.click();
		}
	}
	
	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public void waitToElementDisplayed (WebDriver driver, String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}
	
	public void waitToElementDisplayed (WebDriver driver, String locator, String...values) {
		byXpath = byXpathLocator(locator, values);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}
	
	public void waitToElementInvisible (WebDriver driver, String locator) {
		date = new Date();
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			System.out.println("Start time for wait invisible = " + date.toString());
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byXpath));
		} catch (TimeoutException ex) {
			ex.printStackTrace();
		}
		System.out.println("End time for wait invisible = " + new Date().toString());
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		
	}
	
	public void waitToElementClickable (WebDriver driver, String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
	}
	
	public void waitToElementClickable (WebDriver driver, String locator, String...values) {
		byXpath = byXpathLocator(locator, values);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
	}
	
    public void waitToElementVisible(WebDriver driver, String locator) {
    	byXpath = byXpathLocator(locator);
    	waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
    	overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
    	try {
    		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
    	} catch(Exception ex){
    		ex.printStackTrace();
    	}
    	overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
    }
		

	public void waitToElementVisible(WebDriver driver, String locator, String... values) {
		byXpath = byXpathLocator(locator, values);
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}
	
	public boolean isDataSortedAscending (WebDriver driver, String locator) {
		//khai bao 1 arraylist
		ArrayList<String> arrayList = new ArrayList<>();
		//tim tat ca element matching voi dieu kien
		List<WebElement> elementList = findElementsByXpath(driver, locator);
		//lay text cua tung element add vao arraylist
		for (WebElement element:elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("----------Du lieu tren UI-----------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		//copy 1 arraylist moi de Sort trong code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child:arrayList) {
			sortedList.add(child);
		}
		//thuc hien sort ASC
		Collections.sort(arrayList);
		System.out.println("---------Du lieu da sort ASC trong code---------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		//verify 2 array bang nhau - neu du lieu sort tren UI khong chinh xac thi tra ve fail
		return sortedList.equals(arrayList);
	}
	
	public boolean isDataSortedDescending (WebDriver driver, String locator) {
		//khai bao 1 arraylist
		ArrayList<String> arrayList = new ArrayList<>();
		//tim tat ca element matching voi dieu kien
		List<WebElement> elementList = findElementsByXpath(driver, locator);
		//lay text cua tung element add vao arraylist
		for (WebElement element:elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("----------Du lieu tren UI-----------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		//copy 1 arraylist moi de Sort trong code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child:arrayList) {
			sortedList.add(child);
		}
		//thuc hien sort ASC
		Collections.sort(arrayList);
		System.out.println("---------Du lieu da sort ASC trong code---------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		//reverse data de sort desc
		Collections.reverse(arrayList);
		System.out.println("---------Du lieu da sort DESC trong code---------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		//verify 2 array bang nhau - neu du lieu sort tren UI khong chinh xac thi tra ve fail
		return sortedList.equals(arrayList);
	}
		
	
	//nopCommerce
	
	public FooterMyAccountPage openMyAccountPage(WebDriver driver) {
		waitToElementClickable(driver, AbstractNopCommercePageUI.FOOTER_MY_ACCOUNT_LINK);
		clickToElement(driver, AbstractNopCommercePageUI.FOOTER_MY_ACCOUNT_LINK);
		return PageGeneratorManager.getFooterMyAccountPage(driver);
	}


	public FooterSearch openFooterSearchPage(WebDriver driver) {
		waitToElementClickable(driver, AbstractNopCommercePageUI.FOOTER_SEARCH_LINK);
		clickToElement(driver, AbstractNopCommercePageUI.FOOTER_SEARCH_LINK);
		return PageGeneratorManager.getFooterSearch(driver);
	}
	
	public FooterNewProduct openNewProductPage(WebDriver driver) {
		waitToElementClickable(driver, AbstractNopCommercePageUI.FOOTER_NEW_PRODUCT_LINK);
		clickToElement(driver, AbstractNopCommercePageUI.FOOTER_NEW_PRODUCT_LINK);
		return PageGeneratorManager.getFooterNewProduct(driver);
	}
	
	public HomePage openHomePage(WebDriver driver) {
		waitToElementClickable(driver, AbstractNopCommercePageUI.HOME_PAGE);
		clickToElement(driver, AbstractNopCommercePageUI.HOME_PAGE);
		return PageGeneratorManager.getHomePageObject(driver);
	}
	
	
	
	//truong hop it page (10-15 pages)
	public AbstractPages openFooterSearchByName (WebDriver driver, String pageName) {
		waitToElementDisplayed(driver, AbstractNopCommercePageUI.DYNAMIC_FOOTER_LINK, pageName);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_FOOTER_LINK, pageName);
		switch (pageName) {
		case "Search":
			return PageGeneratorManager.getFooterSearch(driver);
		case "New products":
			return PageGeneratorManager.getFooterNewProduct(driver);
		default:
			return PageGeneratorManager.getFooterMyAccountPage(driver);
			
		}
		
	}
	//truong hop nhieu page
	public void openFootersSearchByName (WebDriver driver, String pageName) {
		waitToElementDisplayed(driver, AbstractNopCommercePageUI.DYNAMIC_FOOTER_LINK, pageName);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_FOOTER_LINK, pageName);
	}
	
	public void openHeaderSearchByName (WebDriver driver, String pageName) {
		waitToElementDisplayed(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_LINK, pageName);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_LINK, pageName);
	}
	
	public void openHeaderMenuSearchByName (WebDriver driver, String pageMenuName, String subMenu) {
		waitToElementVisible(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_MENU_LINK, pageMenuName);
		hoverMouseToElement(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_MENU_LINK, pageMenuName);
		waitToElementDisplayed(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_MENU_LINK, subMenu);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_HEADER_MENU_LINK, subMenu);
	}
	
	public void inputToTextboxByID (WebDriver driver, String textboxID, String value) {
		waitToElementDisplayed(driver, AbstractNopCommercePageUI.DYNAMIC_TEXTBOX, textboxID);
		sendkeyToElement(driver, AbstractNopCommercePageUI.DYNAMIC_TEXTBOX, value, textboxID);
	}
	
	public void clickToRadioButtonID (WebDriver driver, String radioButtonID) {
		waitToElementDisplayed(driver, AbstractNopCommercePageUI.DYNAMIC_RADIO, radioButtonID);
		clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_RADIO, radioButtonID);
	}
	
	public void selectDropdownByName (WebDriver driver, String dropdownName, String value) {
		waitToElementDisplayed(driver, AbstractNopCommercePageUI.DYNAMIC_DROPDOWN_LIST, dropdownName);
		selectItemInHtmlDropdown(driver, AbstractNopCommercePageUI.DYNAMIC_DROPDOWN_LIST, value, dropdownName);
		//clickToElement(driver, AbstractNopCommercePageUI.DYNAMIC_DROPDOWN_LIST, dropdownName);
	}
	
	public boolean isNameDataSortedAscendingNop (WebDriver driver) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<WebElement> elementList = findElementsByXpath(driver, AbstractNopCommercePageUI.PRODUCT_TITLE);
		for (WebElement element:elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("----------Du lieu tren UI-----------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child:arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("---------Du lieu da sort ASC trong code---------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}
	
	public boolean isNameDataSortedDescendingNop (WebDriver driver) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<WebElement> elementList = findElementsByXpath(driver, AbstractNopCommercePageUI.PRODUCT_TITLE);
		for (WebElement element:elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("----------Du lieu tren UI-----------");
		for (String name : arrayList) {
			System.out.println(name);
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("---------Du lieu da sort ASC trong code---------");
		for (String name : arrayList) {
			System.out.println(name);
		}
		Collections.reverse(arrayList);
		System.out.println("---------Du lieu da sort DESC trong code---------");
		for (String name : arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}
	
	public boolean isPriceDataSortedAscendingNop (WebDriver driver) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<WebElement> elementList = findElementsByXpath(driver, AbstractNopCommercePageUI.PRODUCT_PRICE);
		for (WebElement element:elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("----------Du lieu tren UI-----------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child:arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("---------Du lieu da sort ASC trong code---------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}
	
	public boolean isPriceDataSortedDescendingNop (WebDriver driver) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<WebElement> elementList = findElementsByXpath(driver, AbstractNopCommercePageUI.PRODUCT_PRICE);
		for (WebElement element:elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("----------Du lieu tren UI-----------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child:arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		System.out.println("---------Du lieu da sort ASC trong code---------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		Collections.reverse(arrayList);
		System.out.println("---------Du lieu da sort DESC trong code---------");
		for (String name:arrayList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}
	
	//Bank Guru demo
	
	public void openBankGuruPage (WebDriver driver, String pageName) {
		waitToElementDisplayed(driver, AbstractBackGuruPageUI.DYNAMIC_MENU_LINK, pageName);
		clickToElement(driver, AbstractBackGuruPageUI.DYNAMIC_MENU_LINK, pageName);
	}
	
	public void inputToTextBoxBankGuruByName (WebDriver driver, String valueName, String value) {
		waitToElementDisplayed(driver, AbstractBackGuruPageUI.DYNAMIC_TEXTBOX, valueName);
		if (valueName.contains("dob")) {
			removeAttributeInDOM(driver, AbstractBackGuruPageUI.DYNAMIC_TEXTBOX, "type", valueName);
		}
		sendkeyToElement(driver, AbstractBackGuruPageUI.DYNAMIC_TEXTBOX, value, valueName);
	}
	
	public void inputToTextAreaBankGuru (WebDriver driver, String valueName, String value) {
		waitToElementDisplayed(driver, AbstractBackGuruPageUI.DYNAMIC_TEXTAREA, valueName);
		sendkeyToElement(driver, AbstractBackGuruPageUI.DYNAMIC_TEXTAREA, value, valueName);
	}
	
	public void clickToBankGuruButton (WebDriver driver, String valueName) {
		waitToElementClickable(driver, AbstractBackGuruPageUI.DYNAMIC_BUTTON, valueName);
		clickToElement(driver, AbstractBackGuruPageUI.DYNAMIC_BUTTON, valueName);
	}
	
	public void clickToBankGuruRadioButton (WebDriver driver, String valueName) {
		waitToElementClickable(driver, AbstractBackGuruPageUI.DYNAMIC_RADIO, valueName);
		clickToElement(driver, AbstractBackGuruPageUI.DYNAMIC_RADIO, valueName);
	}
	
	public String getBankGuruHeaderText(WebDriver driver) {
		waitToElementDisplayed(driver, AbstractBackGuruPageUI.HEADING);
		return getTextElement(driver, AbstractBackGuruPageUI.HEADING);
	}
	
	public String getBankGuruRowText(WebDriver driver, String values) {
		waitToElementDisplayed(driver, AbstractBackGuruPageUI.DYNAMIC_ROW_NAME, values);;
		return getTextElement(driver, AbstractBackGuruPageUI.DYNAMIC_ROW_NAME, values);
	}
	
	public boolean isBankGuruTextboxEnabled(WebDriver driver, String textboxName) {
		waitToElementVisible(driver, AbstractBackGuruPageUI.DYNAMIC_TEXTBOX, textboxName);
		return isElementEnabled(driver, AbstractBackGuruPageUI.DYNAMIC_TEXTBOX, textboxName);
	}
	
	public void selectDropdowninBankGuru (WebDriver driver, String dropdownName, String valueItem) {
		waitToElementVisible(driver, AbstractBackGuruPageUI.DYNAMIC_DROPDOWN, dropdownName);
		selectItemInHtmlDropdown(driver, AbstractBackGuruPageUI.DYNAMIC_DROPDOWN, valueItem, dropdownName);
	}
}

