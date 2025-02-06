package restassured.class02;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonToken;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
 Different ways to create POST request body:

1.Post request body using Hashmap
2.Post request body creation using Org.JSON
3.Post request body creation using POJO class
4.Post request using external JSON file data
 */
public class DiffWaysToCreateAPostRequestBody {
	
	//1.Post request body using Hashmap
	//@Test(priority = 1)
	public void testPostUsingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "John");
		data.put("location", "Los Angeles");
		data.put("phone", "123456789");
			
		String CourseArray[]= {"C","Python"};
		data.put("courses", CourseArray);
		
		given()
		//.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name",equalTo("John") )
		.body("location", equalTo("Los Angeles"))
		.body("phone", equalTo("123456789"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("Python"))
		//.header("contentType", "application/json")
		.log().all();	
	}
	
	//2.Post request body creation using Org.JSON
	//@Test(priority = 2)
	public void testPostUsingOrgJsonLibraby() {
		JSONObject data = new JSONObject();
		data.put("name", "Scott");
		data.put("location", "CVG");
		data.put("phone", "123456789");

		String CourseArray[]= {"C","C++"};
		data.put("courses", CourseArray);
		
		given()
		//.contentType("application/json")
		.body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name",equalTo("Scott") )
		.body("location", equalTo("CVG"))
		.body("phone", equalTo("123456789"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		//.header("contentType", "application/json")
		.log().all();	
	}
	
	//3.Post request body creation using POJO Class
	//@Test(priority = 3)
		public void testPostUsingPOJO() {
		POJO_PostRequest data = new POJO_PostRequest();
		data.setName("Scott");
		data.setLocation("Columbus");
		data.setPhone("123456789");
		
		String CourseArray[]= {"C","C++"};
		data.setCourses(CourseArray);
			
		given()
		//.contentType("application/json")
		.body(data)
			
		.when()
		.post("http://localhost:3000/students")
			
		.then()
		.statusCode(201)
		.body("name",equalTo("Scott") )
		.body("location", equalTo("Columbus"))
		.body("phone", equalTo("123456789"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		//.header("contentType", "application/json")
		.log().all();	
		}
		
		
		//4.Post request using external JSON file data
		@Test(priority = 4)
		public void testPostUsingExternalJsonFile() throws FileNotFoundException {
		    String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/postBody.json";
		    File f = new File(filePath);
		    FileReader fr = new FileReader(f);
		    JSONTokener jt = new JSONTokener(fr);
		    JSONObject data = new JSONObject(jt);

		    // Log the data to verify its contents
		    System.out.println(data.toString(4)); // Pretty print with 4 spaces

		    given()
		        //.contentType("application/json")
		        .body(data.toString())
		    .when()
		        .post("http://localhost:3000/students")
		        
		    .then()
		        .statusCode(201)
		        .body("name", equalTo("Rupesh Chilkamari"))
		        .body("location", equalTo("Sironcha"))
		        .body("phone", equalTo("1234567890"))
		        .body("courses[0]", equalTo("Maths"))
		        .body("courses[1]", equalTo("Science"))
		        //.header("contentType", "application/json")
		        .log().all();
		}

}
