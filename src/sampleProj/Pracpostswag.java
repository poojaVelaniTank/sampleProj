package sampleProj;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
public class Pracpostswag {

	public static void main(String[] args) {
		//Declare baseURL
		String baseURI="https://petstore.swagger.io";
		String requestbody="{\r\n"
				+ "  \"id\": 1,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 2,\r\n"
				+ "    \"name\": \"testing\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}";
		System.out.println(requestbody);
		RestAssured.baseURI=baseURI;
		//Configure
		int statusCode=given().header("Content-Type","application/json").body(requestbody).when().
				post("v2/pet").then().extract().statusCode();
		String responsebody=given().header("Content-Type","application/json").body(requestbody).when().
				post("v2/pet").then().extract().response().asString();
		System.out.println(statusCode);
		System.out.println(responsebody);
		
		//generate date in required format
		LocalDateTime Date=LocalDateTime.now();
		String expdate=Date.toString().substring(0,13);
		
		//Parse the requestbody
		JsonPath jspreq=new JsonPath(requestbody);
		String req_name=jspreq.getString("name");
		String req_job=jspreq.getString("job");
		
		//Parse the responsebody
		JsonPath jsp=new JsonPath(responsebody);
		String res_name=jsp.getString("name");
		String res_job=jsp.getString("job");
		String res_id=jsp.getString("id");
		String res_createdAt=jsp.getString("createdAt");
		String res_Date=res_createdAt.substring(0,13);
		
		//valdiate
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertNotNull("res_id");
		Assert.assertEquals(res_createdAt,res_Date);
		}
}
