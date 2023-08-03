package sampleProj;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import java.time.LocalDateTime;
import org.testng.Assert;
public class post221 {

	public static void main(String[] args) {
		//Declare baseURI and requestbody
		String baseURI="https://reqres.in/";
		String requestbody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		//Configure API
		RestAssured.baseURI=baseURI;
		//Configure
		int statusCode=given().header("Content-Type","application/json").
				body(requestbody).when().post("/api/users/").then().extract().statusCode();
		String responsebody=given().header("Content-Type","application/json").body(requestbody)
				.when().post("/api/users/").then().extract().response().asString();
		System.out.println(statusCode);
		System.out.println(responsebody);
		
		//generate date in required format
		LocalDateTime Date=LocalDateTime.now();
		String expdate=Date.toString().substring(0,13);
		
		//Extract request body
		JsonPath jspreq=new JsonPath(requestbody);
		String req_name=jspreq.getString("name");
		String req_job=jspreq.getString("job");
				
		//parse the responsebody
		JsonPath jsp=new JsonPath(responsebody);
		String res_name=jsp.getString("name");
		String res_job=jsp.getString("job");
		String res_id=jsp.getString("id");
		String res_createdAt=jsp.getString("createdAt");
		String res_date=res_createdAt.substring(0,13);
		
		//Validate responsebody
		Assert.assertEquals(statusCode,201);
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt, res_date);
		}
}

