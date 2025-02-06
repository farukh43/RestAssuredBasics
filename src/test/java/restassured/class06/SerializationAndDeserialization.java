package restassured.class06;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationAndDeserialization {
	
	//POJO to JSON ---> Serialization
	@Test(priority = 1)
	void convertPOJOtoJson() throws JsonProcessingException {
		//Created java object using POJO Class
		
		Student std = new Student();
		std.setName("John");
		std.setLocation("Los Angeles");
		std.setPhone("123456789");
			
		String CourseArray[]= {"C","Python"};
		std.setCourses(CourseArray);
		
		//convert java Object --> json Object 
		//Import the ObjectMapper from  com.fasterxml.jackson.databind.ObjectMapper;
		ObjectMapper obj = new ObjectMapper();
		String jsonData = obj.writerWithDefaultPrettyPrinter().writeValueAsString(std);
		System.out.println(jsonData);
		
	}
	
	
	
	@Test(priority = 2)
	void convertJsonToPOJO() throws JsonProcessingException {
		//Created JSON to POJO 
		
		String Jsondata = "{\r\n"
				+ "  \"name\" : \"John\",\r\n"
				+ "  \"location\" : \"Los Angeles\",\r\n"
				+ "  \"phone\" : \"123456789\",\r\n"
				+ "  \"courses\" : [ \"C\", \"Python\" ]\r\n"
				+ "}";
	
		//converting json Data to POJO Object
		
		ObjectMapper objmapper = new ObjectMapper();
		Student stdPojo = objmapper.readValue(Jsondata, Student.class);
		System.out.println("CONVERTED NAME :"+stdPojo.getName());
		System.out.println("CONVERTED NUMBER :"+stdPojo.getPhone());
		System.out.println("CONVERTED LOCATION :"+stdPojo.getLocation());
		System.out.println("CONVERTED COURSE 1 :"+stdPojo.getCourses()[0]);
		System.out.println("CONVERTED COURSE 2 :"+stdPojo.getCourses()[1]);
		
}
}
