package restassured.class07;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenarator {
	
	@Test(priority = 1)
	void fakerDataGenarator() {
		Faker faker = new Faker();
		String fullname = faker.name().fullName();
		String firstnam = faker.name().firstName();
		String lastnam = faker.name().lastName();
		
		String username = faker.name().username();
		String password = faker.internet().password();
		
		String phoneNo = faker.phoneNumber().cellPhone();
		
		String email = faker.internet().safeEmailAddress();
		
		System.out.println(fullname);
		System.out.println(firstnam);
		
		System.out.println(lastnam);
		System.out.println("username :" +username);
		System.out.println("password :"+password);
		System.out.println(phoneNo);
		System.out.println(email);
		
	}

}
