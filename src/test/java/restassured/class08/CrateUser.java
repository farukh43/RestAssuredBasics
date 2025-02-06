package restassured.class08;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CrateUser {
    
    @Test(priority = 1)
    public void createUser(ITestContext context) {
        Faker faker = new Faker();
        
        JSONObject data = new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender", faker.demographic().sex());
        data.put("email", faker.internet().safeEmailAddress());
        data.put("status", "inactive");
        
        System.out.println("Create User: " + data);
        
        String BearerToken = "9359ab1c4734340b375ca2284b16d6840515d1440ae1876e3275520e4c7212cd";
        
        int id = given()
            .header("Authorization", "Bearer " + BearerToken)
            .contentType("application/json")
            .body(data.toString())
        
        .when()
            .post("https://gorest.co.in/public/v2/users/")
        .jsonPath().getInt("id");
        
        System.out.println("Generated ID: " + id);
        //context.setAttribute("UserID", id);// This is available only in Test Level
        context.getSuite().setAttribute("UserID", id); //this is recommended 
    }
}
