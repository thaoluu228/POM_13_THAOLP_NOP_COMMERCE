package com.demo.java;

public class StringFormat {

	public static void main(String[] args) {
		String DYNAMIC_FOOTER_LINK = "//div[@class='footer']//a[text()='%s']";
		String DYNAMIC_FOOTER_HEADER_LINK = "//div[@class='%s']//a[text()='%s']";
		String DYNAMIC_COUNTRY_LINK = "//td[text()='%s']/following-sibling::td[@data-key='total']";
		
		clickToFooterLink(DYNAMIC_FOOTER_LINK, "search");
		clickToFooterLink(DYNAMIC_FOOTER_LINK, "homepage");
		clickToFooterLink(DYNAMIC_FOOTER_LINK, "login");
		
//		clickToFooterHeaderLink(DYNAMIC_FOOTER_HEADER_LINK, "header", "homepage");
//		clickToFooterHeaderLink(DYNAMIC_FOOTER_HEADER_LINK, "footer", "login");

		clickToFooterLink(DYNAMIC_COUNTRY_LINK, "Afghanistan");
		clickToFooterLink(DYNAMIC_COUNTRY_LINK, "Mehico");
		}
	
//	public static void clickToFooterLink (String locator, String pageName) {
//		locator = String.format(locator, pageName);
//		System.out.println(locator);
//	}
//	
//	public static void clickToFooterHeaderLink (String locator, String headerFooter, String pageName) {
//		locator = String.format(locator,headerFooter ,pageName);
//		System.out.println(locator);
//	}
//	
	public static void clickToFooterLink (String locator, String...value) {
		locator = String.format(locator,(Object[]) value);
		System.out.println(locator);
	}

}
