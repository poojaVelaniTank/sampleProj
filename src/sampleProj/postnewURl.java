package sampleProj;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import java.time.LocalDateTime;

import org.testng.Assert;
public class postnewURl {

	public static void main(String[] args) {
		//Declare baseURl and requestbody
		String baseURI="https://dummy.restapiexample.com/";
		String requestbody="\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"";
		
		//Configure
		RestAssured.baseURI=baseURI;
		//Configure
		int statuscode=given().header("Content-Type","application/json").body(requestbody).
	when().post("/api/v1/create").then().extract().statusCode();
	String responsebody=given().header("Content-Type","application/json").body(requestbody).
			when().post("/api/v1/create").then().extract().response().asString();
	System.out.println(statuscode);
	System.out.println(responsebody);
	
	//generate date in required format
	LocalDateTime Date = LocalDateTime.now();
	String expdate=Date.toString().substring(0,13);
	
	//Parse request body
	JsonPath req=new JsonPath(requestbody);
	String req_name=req.getString("name");
	String req_job=req.getString("job");
	
	//Parse responsebody
	JsonPath jsp=new JsonPath(responsebody);
	String res_name=jsp.getString("name");
	String res_job=jsp.getString("job");
	String res_id=jsp.getString("id");
	String res_createdAt=jsp.getString("createdAt");
	String res_date=res_createdAt.substring(0,13);
	
	//Validate
	Assert.assertEquals(statuscode, "success");
	Assert.assertEquals(res_name, req_name);
	Assert.assertEquals(res_job, req_job);
	Assert.assertNotNull(res_id);
	Assert.assertEquals(res_createdAt, res_date);
	}
}
