package com.faker.data;

import java.util.Random;

public class RandomData {

	public static void main(String[] args) {
		System.out.println(randomIntNumber());

	}
	
	public static int randomIntNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}

}
