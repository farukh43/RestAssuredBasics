package restassured.class04and05;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
/*
https://petstore.swagger.io/v2/pet/1
 */
public class ParsingResponseXMLdata {

	//@Test(priority = 1)
	public void testXMLResponse() {
		/*
		
		//Approach 1 -- > to verify the specific fields
		 given()
	        .contentType(ContentType.XML)
	        .accept(ContentType.XML)
	    .when()
	        .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
	    .then()
	        .statusCode(200)
	        .header("Content-Type", "application/xml")
	        .body("pets.Pet[0].name", equalTo("fish"));
		
		*/
		
		//Approach 2
		
	Response res = given()
	.contentType(ContentType.XML)
	.accept(ContentType.XML)
			
		.when()
		.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
		
		Assert.assertEquals(res.getStatusCode(),200); //Status Code Validation
		Assert.assertEquals(res.header("Content-Type"), "application/xml"); // Header Validation
		
		String petName=res.getBody().xmlPath().get("pets.Pet[0].name").toString(); //Validating BookName
		Assert.assertEquals(petName, "doggie");
	}
	
	@Test(priority = 2)
	public void testXMLResponseBodyData() {
	    // XMLPath Class
	    // Approach 3 --> We want to perform some operations on response body, then we can use this approach

	    
		Response res = given()
		.contentType(ContentType.XML)
		.accept(ContentType.XML)
					
		.when()
		.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

	    // Log the response body to verify its contents
	   // System.out.println("Response Body: " + res.getBody().asString());

	    // Using XMLpath Class -- finding all pets and its NO
	    XmlPath xmlobj =new XmlPath (res.asString());
	   List <String> allPets= xmlobj.getList("pets.Pet");
	   System.out.println(allPets.size());
	   
	  // Assert.assertEquals(allPets.size(), 241);
	   
	   //verify the specific traveller name is present or name
	   List <String> travellerName=xmlobj.getList("pets.Pet.name");
	   
	   boolean status =false;
	   for (String name : travellerName) {
		System.out.println(name);
		if(name.equals("Andre.Blick91"))
		{
			status=true;
			break;
		}
		
	}
	   
	   Assert.assertEquals(status, true);
	   
	    
	}

}
