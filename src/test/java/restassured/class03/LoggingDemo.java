package restassured.class03;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class LoggingDemo {
	
	@Test(priority = 1)
	public void testHeaders() {
		
		given()
		
		.when()
		.get(" https://reqres.in/api/users?page=2")
		
		.then()
		//.log().body(); // this will print the responce body in the console
		//.log().headers();// this will print the headers in the console
		//.log().cookies();// this will print the coockies in the console
		.log().all();
		
	}
}
