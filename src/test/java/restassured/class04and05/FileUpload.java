package restassured.class04and05;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUpload {
	
//	@Test(priority = 1)
	public void testFileUpload () {
		File myfile =new File("C:\\Users\\Faruk\\Downloads\\AA_NewTestFileApiTest.txt");
		
		given()
		.multiPart("file",myfile)
		.contentType("multipart/form-data")
		.when()
		.post("https://the-internet.herokuapp.com/upload")
		.then()
		//.body("fileName", equalTo("AA_NewTestFileApiTest.txt"))
		.statusCode(200);
		
	}
	
//	@Test(priority = 2)
	public void testMultipleFileUpload () {
		File myfile =new File("C:\\Users\\Faruk\\Downloads\\AA_NewTestFileApiTest.txt");
		File myfile2 =new File("C:\\Users\\Faruk\\Downloads\\AA_NewTestFileApiTest2.txt");
		
		given()
		.multiPart("file",myfile)
		.multiPart("file",myfile2)
		.contentType("multipart/form-data")
		.when()
		.post("https://the-internet.herokuapp.com/upload")
		.then()
		//.body("[0].fileName", equalTo("AA_NewTestFileApiTest.txt"))
		//.body("[1].fileName", equalTo("AA_NewTestFileApiTest2.txt"))
		.statusCode(200);
		
	}

	@Test(priority = 3)
	public void fileDownload() {
		
		given()
		
		.when()
		.get("https://the-internet.herokuapp.com/download/AA_NewTestFileApiTest.txt")
		.then()
		.statusCode(200)
		.log().all();
		
	}

}
