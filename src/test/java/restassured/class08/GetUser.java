package restassured.class08;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	 
	@Test(priority = 1)
	void testGetUser(ITestContext context) {
		String BearerToken = "9359ab1c4734340b375ca2284b16d6840515d1440ae1876e3275520e4c7212cd";
		//int id = 0; // this should come from CreateUser
		//int id= (Integer) context.getAttribute("UserID"); // for class level
		
		int id= (Integer) context.getSuite().getAttribute("UserID"); //for Suite Level -->this is recommended 
		
		given()
		.header("Authorization", "Bearer "+BearerToken)
		.contentType("application/json")
		.pathParam("id", id)
		
		.when()
		.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
		.statusCode(200)
		.log().all();
		
	}

}
