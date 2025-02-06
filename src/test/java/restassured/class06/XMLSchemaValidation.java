package restassured.class06;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;


public class XMLSchemaValidation {
	

	@Test(priority = 1)
	public void XmlSchemaValidation() {
	    Response res = given()
	        .contentType(ContentType.XML)
	        .accept(ContentType.XML)
	    .when()
	        .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

	    // Log the response body to verify its structure
	    System.out.println(res.getBody().asString());

	    res.then()
	        .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("PetsXMLValidation.xsd"));
	}

	
	

}
