package restassured.class03;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

/*
 https://reqres.in/api/users?page=2&id=7 
 */
public class PathAndQueryParams {

	@Test(priority = 1)
	public void testQueyAndPathParam() {
		given()
		.pathParam("mypath","users" ) //path parameters
		.queryParam("page", "2") //Query parameters
		.queryParam("id", "7") //Query parameters
		
		.when()
		.get("https://reqres.in/api/{mypath}") // we need to expilicity need to pass here --> queryParameter will will along with request , no need to pass
			
		.then()
		.statusCode(200)
		.log().all();
	}

}
