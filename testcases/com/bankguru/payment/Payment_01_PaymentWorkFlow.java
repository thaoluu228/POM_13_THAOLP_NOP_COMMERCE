package com.bankguru.payment;

import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObject.bankGuru.DepositPageObject;
import pageObject.bankGuru.EditAccountPageObject;
import pageObject.bankGuru.EditCustomerPageObject;
import pageObject.bankGuru.HomePageObject;
import pageObject.bankGuru.LoginPageObject;
import pageObject.bankGuru.NewAccountPageObject;
import pageObject.bankGuru.NewCustomerPageObject;
import pageObject.bankGuru.PageGeneratorManager;
import pageObject.bankGuru.RegisterPageObject;
import pageObject.bankGuru.TransferPageObject;
import pageObject.bankGuru.WithdrawlPageObject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class Payment_01_PaymentWorkFlow extends AbstractTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private EditCustomerPageObject editCustomerPage;
	private NewAccountPageObject newAccountPage;
	private EditAccountPageObject editAccountPage;
	private DepositPageObject depositPage;
	private WithdrawlPageObject withdrawPage;
	private TransferPageObject transferPage;
	
	String email, customerName, gender, dob, address, city, state, pin, phone, pass, customerID;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;
	String accountSavings, accountCurrent, firstAccountID, secondAccountID;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void beforeTest(String browserName, String autUrl) {

		driver = getBrowserDriver(browserName, autUrl);

		email = "thaoluu" + randomNumber() + "@yopmail.com";
		customerName = "Thao";
		gender = "female";
		dob = "1996-10-03";
		address = "259 Yen Hoa";
		city = "Cau Giay";
		state = "Ha Noi";
		pin = "456890";
		phone = "037684924";
		pass = "310228";

		editAddress = "Trung Yen plaza";
		editCity = "Yen Hoa";
		editState = "Ha Tay";
		editPin = "565678";
		editPhone = "1893792";
		editEmail = "thaoluu" + randomNumber() + "@gmail.com";
		
		accountSavings = "Savings";
		accountCurrent = "Current";

		loginPage = PageGeneratorManager.getLoginPage(driver);

		String loginPageURL = loginPage.getLoginPageURL();

		registerPage = loginPage.clickToHereLink();

		registerPage.inputToEmailTextbox(email);
		registerPage.clickSubmitButton();

		String userID = registerPage.getUserID();
		String password = registerPage.getPassword();

		loginPage = registerPage.openLoginPage(loginPageURL);

		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPassword(password);
		homePage = loginPage.clickToLoginButton();
		verifyTrue(homePage.isWelcomeMessageDisplayed());

	}

	@Test
	public void TC_01_CreateNewCustomer() throws InterruptedException {
		homePage.openBankGuruPage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		newCustomerPage.clickToBankGuruRadioButton(driver, "f");

		newCustomerPage.inputToTextBoxBankGuruByName(driver, "name", customerName);
		newCustomerPage.inputToTextAreaBankGuru(driver, "addr", address);
		newCustomerPage.inputToTextBoxBankGuruByName(driver, "dob", dob);
		newCustomerPage.inputToTextBoxBankGuruByName(driver, "city", city);
		newCustomerPage.inputToTextBoxBankGuruByName(driver, "state", state);
		newCustomerPage.inputToTextBoxBankGuruByName(driver, "pinno", pin);
		newCustomerPage.inputToTextBoxBankGuruByName(driver, "telephoneno", phone);
		newCustomerPage.inputToTextBoxBankGuruByName(driver, "emailid", email);
		newCustomerPage.inputToTextBoxBankGuruByName(driver, "password", pass);
		newCustomerPage.clickToBankGuruButton(driver, "Submit");

		Thread.sleep(2000);
		verifyEquals(newCustomerPage.getBankGuruHeaderText(driver), "Customer Registered Successfully!!!");
		verifyEquals(newCustomerPage.getBankGuruRowText(driver, "Customer Name"), customerName);
		verifyEquals(newCustomerPage.getBankGuruRowText(driver, "Gender"), gender);
		verifyEquals(newCustomerPage.getBankGuruRowText(driver, "Birthdate"), dob);
		verifyEquals(newCustomerPage.getBankGuruRowText(driver, "Address"), address);
		verifyEquals(newCustomerPage.getBankGuruRowText(driver, "City"), city);
		verifyEquals(newCustomerPage.getBankGuruRowText(driver, "Pin"), pin);
		verifyEquals(newCustomerPage.getBankGuruRowText(driver, "Mobile No."), phone);
		verifyEquals(newCustomerPage.getBankGuruRowText(driver, "State"), state);
		verifyEquals(newCustomerPage.getBankGuruRowText(driver, "Email"), email);

		// get customer ID
		customerID = newCustomerPage.getBankGuruRowText(driver, "Customer ID");
	}

	@Test
	public void TC_02_EditCustomer() {
		newCustomerPage.openBankGuruPage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		editCustomerPage.inputToTextBoxBankGuruByName(driver, "cusid", customerID);
		editCustomerPage.clickToBankGuruButton(driver, "Submit");

		// verify a field disable
		verifyFalse(editCustomerPage.isBankGuruTextboxEnabled(driver, "name"));
		verifyFalse(editCustomerPage.isBankGuruTextboxEnabled(driver, "dob"));
		verifyFalse(editCustomerPage.isBankGuruTextboxEnabled(driver, "gender"));

		editCustomerPage.inputToTextAreaBankGuru(driver, "addr", editAddress);
		editCustomerPage.inputToTextBoxBankGuruByName(driver, "city", editCity);
		editCustomerPage.inputToTextBoxBankGuruByName(driver, "state", editState);
		editCustomerPage.inputToTextBoxBankGuruByName(driver, "pinno", editPin);
		editCustomerPage.inputToTextBoxBankGuruByName(driver, "telephoneno", editPhone);
		editCustomerPage.inputToTextBoxBankGuruByName(driver, "emailid", editEmail);

		editCustomerPage.clickToBankGuruButton(driver, "Submit");
		
		verifyEquals(editCustomerPage.getBankGuruHeaderText(driver), "Customer details updated Successfully!!!");
		verifyEquals(editCustomerPage.getBankGuruRowText(driver, "Customer Name"), customerName);
		verifyEquals(editCustomerPage.getBankGuruRowText(driver, "Gender"), gender);
		verifyEquals(editCustomerPage.getBankGuruRowText(driver, "Birthdate"), dob);
		verifyEquals(editCustomerPage.getBankGuruRowText(driver, "Address"), editAddress);
		verifyEquals(editCustomerPage.getBankGuruRowText(driver, "City"), editCity);
		verifyEquals(editCustomerPage.getBankGuruRowText(driver, "Pin"), editPin);
		verifyEquals(editCustomerPage.getBankGuruRowText(driver, "Mobile No."), editPhone);
		verifyEquals(editCustomerPage.getBankGuruRowText(driver, "State"), editState);
		verifyEquals(editCustomerPage.getBankGuruRowText(driver, "Email"), editEmail);
	}

	@Test
	public void TC_03_AddNewAccount() {
		//Create first account
		editCustomerPage.openBankGuruPage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		
		newAccountPage.inputToTextBoxBankGuruByName(driver, "cusid", customerID);
		newAccountPage.selectDropdowninBankGuru(driver, "selaccount", accountSavings);
		newAccountPage.inputToTextBoxBankGuruByName(driver, "inideposit", "2000");
		newAccountPage.clickToBankGuruButton(driver, "submit");
		
		verifyEquals(newAccountPage.getBankGuruHeaderText(driver), "Account Generated Successfully!!!");
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Customer ID"), customerID);
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Customer Name"), customerName);
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Email"), editEmail);
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Account Type"), accountSavings);
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Current Amount"), "2000");
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Date of Opening"), getToday());
		
		firstAccountID = newAccountPage.getBankGuruRowText(driver, "Account ID");
		
		//Create second account
		editCustomerPage.openBankGuruPage(driver, "New Account");
		
		newAccountPage.inputToTextBoxBankGuruByName(driver, "cusid", customerID);
		newAccountPage.selectDropdowninBankGuru(driver, "selaccount", accountSavings);
		newAccountPage.inputToTextBoxBankGuruByName(driver, "inideposit", "1000");
		newAccountPage.clickToBankGuruButton(driver, "submit");
		
		verifyEquals(newAccountPage.getBankGuruHeaderText(driver), "Account Generated Successfully!!!");
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Customer ID"), customerID);
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Customer Name"), customerName);
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Email"), editEmail);
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Account Type"), accountSavings);
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Current Amount"), "1000");
		verifyEquals(newAccountPage.getBankGuruRowText(driver, "Date of Opening"), getToday());
		
		secondAccountID = newAccountPage.getBankGuruRowText(driver, "Account ID");

	}

	@Test
	public void TC_04_EditAccount() {
		newAccountPage.openBankGuruPage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		
		editAccountPage.inputToTextBoxBankGuruByName(driver, "accountno", secondAccountID);
		editAccountPage.clickToBankGuruButton(driver, "Submit");
		
		verifyFalse(editAccountPage.isBankGuruTextboxEnabled(driver, "txtcid"));
		verifyFalse(editAccountPage.isBankGuruTextboxEnabled(driver, "txtinitdep"));
		
		editAccountPage.selectDropdowninBankGuru(driver, "a_type", accountCurrent);
		editAccountPage.clickToBankGuruButton(driver, "Submit");
		
		verifyEquals(editAccountPage.getBankGuruHeaderText(driver), "Account details updated Successfully!!!");
		verifyEquals(editAccountPage.getBankGuruRowText(driver, "Customer ID"), customerID);
		verifyEquals(editAccountPage.getBankGuruRowText(driver, "Account ID"), secondAccountID);
		verifyEquals(editAccountPage.getBankGuruRowText(driver, "Customer Name"), customerName);
		verifyEquals(editAccountPage.getBankGuruRowText(driver, "Email"), editEmail);
		verifyEquals(editAccountPage.getBankGuruRowText(driver, "Account Type"), accountCurrent);
		verifyEquals(editAccountPage.getBankGuruRowText(driver, "Current Amount"), "1000");
		verifyEquals(editAccountPage.getBankGuruRowText(driver, "Date of Opening"), getToday());

	}

	@Test
	public void TC_05_DepositToCurrentAccount() {
		editAccountPage.openBankGuruPage(driver, "Deposit");
		depositPage = PageGeneratorManager.getDepositPage(driver);
		
		depositPage.inputToTextBoxBankGuruByName(driver, "accountno", firstAccountID);
		depositPage.inputToTextBoxBankGuruByName(driver, "ammount", "1000");
		depositPage.inputToTextBoxBankGuruByName(driver, "desc", "nap tien");
		depositPage.clickToBankGuruButton(driver, "Submit");
		
		verifyEquals(depositPage.getBankGuruHeaderText(driver), "Transaction details of Deposit for Account" + firstAccountID);
		verifyEquals(depositPage.getBankGuruRowText(driver, "Account No"), firstAccountID);
		verifyEquals(depositPage.getBankGuruRowText(driver, "Amount Credited"), "1000");
		verifyEquals(depositPage.getBankGuruRowText(driver, "Type of Transaction"), "Deposit");
		verifyEquals(depositPage.getBankGuruRowText(driver, "Description"), "nap tien");
		verifyEquals(depositPage.getBankGuruRowText(driver, "Current Balance"), "3000");
		
	}

	@Test
	public void TC_06_WithdrawFromCurrentAccount() {
		depositPage.openBankGuruPage(driver, "Withdrawal");
		withdrawPage = PageGeneratorManager.getWithdrawlPage(driver);
		
		withdrawPage.inputToTextBoxBankGuruByName(driver, "accountno", firstAccountID);
		withdrawPage.inputToTextBoxBankGuruByName(driver, "ammount", "500");
		withdrawPage.inputToTextBoxBankGuruByName(driver, "desc", "rut tien");
		withdrawPage.clickToBankGuruButton(driver, "Submit");
		
		verifyEquals(withdrawPage.getBankGuruHeaderText(driver), "Transaction details of Withdrawal for Account" + firstAccountID);
		verifyEquals(withdrawPage.getBankGuruRowText(driver, "Account No"), firstAccountID);
		verifyEquals(withdrawPage.getBankGuruRowText(driver, "Amount Debited"), "500");
		verifyEquals(withdrawPage.getBankGuruRowText(driver, "Type of Transaction"), "Withdrawal");
		verifyEquals(withdrawPage.getBankGuruRowText(driver, "Description"), "rut tien");
		verifyEquals(withdrawPage.getBankGuruRowText(driver, "Current Balance"), "3500");

	}

	@Test
	public void TC_07_TransferToAnotherAccount() {
		withdrawPage.openBankGuruPage(driver, "Fund Transfer");
		

	}

	@Test
	public void TC_08_CheckAccountBalance() {

	}

	@Test
	public void TC_09_DeleteAllAccount() {

	}

	@Test
	public void TC_10_DeleteCustomer() {

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
