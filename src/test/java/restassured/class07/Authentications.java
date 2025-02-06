package restassured.class07;

import org.testng.annotations.Test;

import io.restassured.http.Headers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {
	@Test(priority = 1)
	public void testBasicAuthentications() {
		given()
		.auth().basic("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true));		
	}
	
	@Test(priority = 2)
	public void testDigestAuthentications() {
		given()
		.auth().digest("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true));		
	}
	
	@Test(priority = 3)
	public void testpreemptivetAuthentications() {
		given()
		.auth().preemptive().basic("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true));		
	}
	
	@Test(priority = 4)
	public void testBearerTokenAuthentications() {
		String gitHubearerToken = "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";

		//BearerToken will be sent in header as -->Bearer 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

		given()
		.headers("Authorization","Bearer "+gitHubearerToken)
		
		.when()
		.get("https://api.github.com/user/repos")
		
		.then()
		.statusCode(200)
		.log().all();		
	}
	
	//This is just template of Oauth1 --> it is mostly depricated
	public void testOauth1Authentications() {
		given()
		.auth().oauth("consumerkey", "consumersecret", "accessToke", "tokenSecrete")
		
		.when()
		.get("URL")
		
		.then()
		.statusCode(200);
	}
	

	    //@Test
	    public void testOAuth2Authentication() {
	        given()
	            .auth().oauth2("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") // Replace with your actual token
	        .when()
	            .get("https://api.github.com/user/repos")
	        .then()
	            .statusCode(200)
	            .log().all();
	    }
	
	    @Test(priority = 5)
	public void testAPIKeyAuthentication() {
		
	    /*
	    // Approach 1
		given()
		.queryParam("appid", "c9298c77cf6f24261ca05ae2d0645160")
		
		.when()
		.get("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99")
		
		.then()
		.statusCode(200)
		.log().all();
		*/
	    	
	    // Approach 2
	    	
	    given()
		.queryParam("appid", "c9298c77cf6f24261ca05ae2d0645160")
		
		.pathParam("mypath", "data/2.5/weather")
		
		.queryParam("lat", "44.34")
		.queryParam("lon", "10.99")
		
	    .when()
	    .get("https://api.openweathermap.org/{mypath}")
	    
	    .then()
	    .statusCode(200)
	    .log().all();
	}
	    

}
