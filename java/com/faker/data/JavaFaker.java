package com.faker.data;

import java.util.Locale;

import com.github.javafaker.Faker;

public class JavaFaker {
	//language
	private Locale locale = new Locale("en");
	//init with language
	private Faker faker = new Faker(locale);
	
	
	public static void main(String[] args) {
		JavaFaker fake = new JavaFaker();
		System.out.println(fake.getFullName());
		System.out.println(fake.getAddress());
		System.out.println(fake.getEmail());
		System.out.println(fake.getPhone());
		System.out.println(fake.getCityName());
		System.out.println(fake.getCity());
	}
	
	public String getFullName() {
		return faker.name().fullName();
	}
	
	public String getAddress() {
		return faker.address().streetAddress();
	}

	public String getEmail() {
		return faker.internet().emailAddress();
	}	
	
	public String getPhone() {
		return faker.phoneNumber().phoneNumber();
	}
	
	public String getCity() {
		return faker.address().city();
	}
	
	public String getCityName() {
		return faker.address().cityName();
	}



}
