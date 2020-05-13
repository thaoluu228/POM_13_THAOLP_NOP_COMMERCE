package commons;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {
	
	//language
		private Locale locale = new Locale("en");
		//init with language
		private Faker faker = new Faker(locale);
		
		public static DataHelper getData() {
			return new DataHelper();
		}
		
		public String getFirstName() {
			return faker.name().firstName();
		}
		
		public String getLastName() {
			return faker.name().lastName();
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
		
		public String getPassword() {
			return faker.internet().password(6, 18);
		}
		
		public String getCompany() {
			return faker.company().name();
		}

}
