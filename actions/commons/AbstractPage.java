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

public class AbstractPage {
	private WebDriver driver;
	long longTimeout = 30;
	private Select select;
	private Actions action;
	private WebElement element;
	private WebDriverWait waitExplicit;
	
	public AbstractPage(WebDriver localDriver) {
		driver = localDriver;
	}
	
	
	//Mo ra 1 url tu ben ngoai
	public void openUrl (String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	public String getPageTitle () {
		return driver.getTitle();
	}
	
	public String getCurrentPageUrl ( ) {
		return driver.getCurrentUrl();
	}

	public void back () {
		driver.navigate().back();
	}
	
	public void refreshPage () {
		driver.navigate().refresh();
	}
	
	public void acceptAlert () {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert () {
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert () {
		return driver.switchTo().alert().getText();
	}
	
	public String getTextElement (String locator) {
		return findElementByXpath(locator).getText();
	}
	
	public void sendkeyToAlert (String value) {
		driver.switchTo().alert().sendKeys(value);
	}
	
	public WebElement findElementByXpath (String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public List<WebElement> findElementsByXpath (String locator) {
		return driver.findElements(By.xpath(locator));
	}
	
	public void clickToElement (String locator) {
		findElementByXpath(locator).click();
	}
	
	public void sendkeyToElement (String locator, String value) {
		findElementByXpath(locator).sendKeys(value);
	}
	
	public int countElementNumber (String locator) {
		return findElementsByXpath(locator).size();
	}
	
	public String getAttributeElement (String locator, String value) {
		return findElementByXpath(locator).getAttribute(value);
	}
	
	public boolean isElementDisplayed (String locator) {
		return findElementByXpath(locator).isDisplayed();
	}

	public void hoverMouseToElement (String locator) {
		action = new Actions(driver);
		element = findElementByXpath(locator);
		action.moveToElement(element).perform();
	}
	
	public void selectItemInHtmlDropdown (String locator, String value) {
		element = findElementByXpath(locator);
		select = new Select(element);
		select.selectByVisibleText(value);
	}
	
	public WebElement getSelectedItemInHtmlDropdown (String locator) {
		element = findElementByXpath(locator);
		select = new Select(element);
		return select.getFirstSelectedOption();
	}
	
	public void selectIteminCustomDropdown(String parentXpath, String allItemsXpath, String expectedText) {
		findElementByXpath(parentXpath).click();

		List<WebElement> allItems = findElementsByXpath(allItemsXpath);

		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		for (WebElement item : allItems) {
			System.out.println("Text =" + item.getText());
			if (item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}
	}
	
	public void inputIteminCustomDropdown(String parentXpath, String inputXpath, String expectedText) {
		findElementByXpath(parentXpath).click();
		findElementByXpath(inputXpath).sendKeys(expectedText);
		action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath(inputXpath)), Keys.ENTER).perform();
	}

	public void sendKeyboardToElement (String locator, Keys key) {
		element = findElementByXpath(locator);
		action = new Actions(driver);
		action.sendKeys(element, key).perform();
	}
	
	public void upload1FileBySendkey (String locator, String value) {
		element = findElementByXpath(locator);
		element.sendKeys(value);
		element.click();
	}
	
	public void uploadMutiFileBySendkey (String locator, String value) {
		element = findElementByXpath(locator);
		element.sendKeys(value);
		List <WebElement> startButton = findElementsByXpath(locator);
		for (WebElement button: startButton ) {
			button.click();
		}
	}
	
	public void waitToElementDisplayed (String locator) {
		element = findElementByXpath(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void waitToElementClickable (String locator) {
		element = findElementByXpath(locator);
		waitExplicit = new WebDriverWait(driver, longTimeout);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}
}
