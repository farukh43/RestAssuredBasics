package restassured.class08;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


public class UpdateUser {
	
	@Test(priority = 1)
	public void testUpdateUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", faker.demographic().sex());
		data.put("email", faker.internet().safeEmailAddress());
		data.put("status", "active");
		System.out.println("Update User :" +data);
		
		String BearerToken = "9359ab1c4734340b375ca2284b16d6840515d1440ae1876e3275520e4c7212cd";
		
		//int id = 0;
		//int id= (Integer) context.getAttribute("UserID"); // for class level
		int id= (Integer) context.getSuite().getAttribute("UserID"); //for Suite Level
		
		given()
		.header("Authorization", "Bearer "+BearerToken)
		.contentType("application/json")
		.body(data.toString())
		.pathParam("id", id)
		
		.when()
		.put("https://gorest.co.in/public/v2/users/{id}")
	
		.then()
		.statusCode(200)
		.log().all();
		
		
	}

}
