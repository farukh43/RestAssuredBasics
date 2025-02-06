package restassured.class06;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;


public class JSONSchemaValidation {
	//Copy pasted the json response and genarated the schema
	@Test(priority = 1)
	public void jsonSchemaValidation() {
		given()
		
		.when()
		.get("http://localhost:3000/store")
		
		.then()
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJSONschema.json"));
	}
	
	//Validate the schema for 2 books from the store
	@Test(priority = 2)
	public void jsonSchemaValidation1() {
		given()
		
		.when()
		.get("http://localhost:3000/store")
		
		.then()
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJSONschema2.json"));
	}

}
