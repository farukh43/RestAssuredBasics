package restassured.class03;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class HeaderDemo {
	
	//@Test(priority = 1)
	public void testHeaders() {
		
		given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.header("Content-Type", "text/html; charset=ISO-8859-1")
		.header("Content-Encoding", "gzip")
		.header("Server", "gws")
		.and()
		.header("X-Frame-Options", "SAMEORIGIN")
		.log().all();
	}
	
	@Test(priority = 2)
	public void getHeaders() {
		
		Response res = given()
		
		.when()
		.get("https://www.google.com/");
		
		//get single header infor
		//String headervalues=res.getHeader("Content-Type");
		//System.out.println("The value of single Content-Type Header    "+  headervalues);
		
		//Get all header info
		Headers myheader =res.headers();
		
		for (Header header : myheader) {
			System.out.println(header.getName() +"						"+ header.getValue());	
		}
		//another way is we can .log().headers(); in then section
	}

}
