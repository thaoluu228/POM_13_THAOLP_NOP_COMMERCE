package commons;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import pageUI.nopCommerce.AbstractPageUI;


public class AbstractPages {
	private long longTimeout = 30;
	private Select select;
	private Actions action;
	private WebElement element;
	private WebDriverWait waitExplicit;
	private By byXpath;
	
	//Mo ra 1 url tu ben ngoai
	public void openUrl (WebDriver driver, String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
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
	
	public void clickToElement (WebDriver driver, String locator, String...values) {
		findElementByXpath(driver,locator, values).click();
	}
	
	public void sendkeyToElement (WebDriver driver, String locator, String value) {
		findElementByXpath(driver, locator).sendKeys(value);
	}
	
	public int countElementNumber (WebDriver driver, String locator) {
		return findElementsByXpath(driver, locator).size();
	}
	
	public String getAttributeElement (WebDriver driver, String locator, String value) {
		return findElementByXpath(driver, locator).getAttribute(value);
	}
	
	public boolean isElementDisplayed (WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isDisplayed();
	}

	public void hoverMouseToElement (WebDriver driver, String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.moveToElement(element).perform();
	}
	
	public void selectItemInHtmlDropdown (WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		select.selectByVisibleText(value);
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
	
	public void waitToElementDisplayed (WebDriver driver, String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}
	
	public void waitToElementDisplayed (WebDriver driver, String locator, String...values) {
		byXpath = byXpathLocator(locator, values);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}
	
	public void waitToElementClickable (WebDriver driver, String locator) {
		byXpath = byXpathLocator(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
	}
	
	public void waitToElementClickable (WebDriver driver, String locator, String...values) {
		byXpath = byXpathLocator(locator, values);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
	}
	
	public FooterMyAccountPage openMyAccountPage(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.FOOTER_MY_ACCOUNT_LINK);
		clickToElement(driver, AbstractPageUI.FOOTER_MY_ACCOUNT_LINK);
		return PageGeneratorManager.getFooterMyAccountPage(driver);
	}


	public FooterSearch openFooterSearchPage(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.FOOTER_SEARCH_LINK);
		clickToElement(driver, AbstractPageUI.FOOTER_SEARCH_LINK);
		return PageGeneratorManager.getFooterSearch(driver);
	}
	
	public FooterNewProduct openNewProductPage(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.FOOTER_NEW_PRODUCT_LINK);
		clickToElement(driver, AbstractPageUI.FOOTER_NEW_PRODUCT_LINK);
		return PageGeneratorManager.getFooterNewProduct(driver);
	}
	
	public HomePage openHomePage(WebDriver driver) {
		waitToElementClickable(driver, AbstractPageUI.HOME_PAGE);
		clickToElement(driver, AbstractPageUI.HOME_PAGE);
		return PageGeneratorManager.getHomePageObject(driver);
	}
	
	//truong hop it page (10-15 pages)
	public AbstractPages openFooterSearchByName (WebDriver driver, String pageName) {
		waitToElementDisplayed(driver, AbstractPageUI.DYNAMIC_FOOTER_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_FOOTER_LINK, pageName);
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
		waitToElementDisplayed(driver, AbstractPageUI.DYNAMIC_FOOTER_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_FOOTER_LINK, pageName);
	}
}
