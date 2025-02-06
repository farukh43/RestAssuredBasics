package restassured.class04and05;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
/*
 https://reqres.in/api/users?page=2&id=7 
 */
public class ParsingResponseJsonData {

	@Test(priority = 1)
	public void testJsonResponse() {
		
		/*
		//Approach 1 -- > to verify the specific fields
		given()		
		.contentType(ContentType.JSON)
		.when()
		.get("http://localhost:3000/store")
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json")
		.body("book[4].title", equalTo("The Hobbit"));
		*/
		
		//Approach 2
		
Response res = given()		
		.contentType(ContentType.JSON)
			
		.when()
		.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(),200); //Status Code Validation
		Assert.assertEquals(res.header("Content-Type"), "application/json"); // Header Validation
		
		String bookName=res.getBody().jsonPath().get("book[4].title").toString(); //Validating BookName
		Assert.assertEquals(bookName, "The Hobbit");
		
	}

	
	@Test(priority = 2)
	public void testJsonResponseBodyData() {
	    // JSONObject Class
	    // Approach 3 --> We want to perform some operations on response body, then we can use this approach
	    // Want to calculate the price of all books
	    
	    Response res = given()
	        .contentType(ContentType.JSON)
	    .when()
	        .get("http://localhost:3000/store");

	    // Log the response body to verify its contents
	    System.out.println("Response Body: " + res.getBody().asString());

	    // Using JSONObject Class
	    JSONObject jo = new JSONObject(res.getBody().asString()); // Converting response to JSON object type

	  //  JSONArray jrr = new JSONArray(res.getBody().asString()) ; //--> if the response is in JSON Array
	    /*
	    // Print all titles of books
	    for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
	        String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
	        System.out.println(bookTitle);
	    }
	    */
	    
	    /*
	    //Validate a specific book name
	    
	    boolean status =false;
	    for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
	        String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
	        
	        if(bookTitle.equals("The Hobbit"))
	        {
	        	status=true;
	        	break;
	        }
	    }
	    Assert.assertEquals(status, true);
	    */
	    
	 // Using for Each loop
	 // Validate a specific book name
	    boolean status1 = false;
	    for (Object obj : jo.getJSONArray("book")) {
	        JSONObject book = (JSONObject) obj;
	        String bookTitle = book.get("title").toString();
	        
	        if (bookTitle.equals("The Hobbit")) {
	            status1 = true;
	            break;
	        }
	    }
	    Assert.assertEquals(status1, true);
	    
	    //Total price of books
	    double totalPrice=0;
	    for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
	        String Price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
	        totalPrice= totalPrice+ Double.parseDouble(Price);	        
	    }
	    System.out.println("Total price of the books is "+ totalPrice);
	    
	    Assert.assertEquals(totalPrice, 365.31);
	    
	}

}
