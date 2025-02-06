package restassured.class03;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {
	
	@Test(priority = 1)
	public void testCookies() {
		
		given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.cookie("AEC","AVcja2c1IWK7aNjfAXpdnF6HloXh368_gevUmJKwzyt07HTYrS3ML-pAXcc")
		.log().all();
		//Coockies will change every time , this test intended to fail
	}
	
	@Test(priority = 2)
	public void getCookiesInfo() {
		
		Response res=given()
		
		.when()
		.get("https://www.google.com/");
		
		//To get a Single cookcie info
		String coocie_info =res.getCookie("AEC");
		System.out.println("Value of Coockie is -->"+coocie_info);
		
		//To get all coockie Info
		Map <String, String> allCoockieValue=res.getCookies();
		
		System.out.println(allCoockieValue.keySet());
		
		for (String k : allCoockieValue.keySet()) {
			String coockieVal=res.getCookie(k);
			System.out.println(k + "       "+coockieVal);
			
		}
		
		
	}

}
