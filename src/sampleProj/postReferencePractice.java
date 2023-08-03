package sampleProj;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.time.LocalDate;import java.time.format.DateTimeFormatter;
import org.testng.Assert;
public class postReferencePractice {
	public static void main(String[] args) {
		//Step 1 : Declare BaseUrl
		RestAssured.baseURI="https://reqres.in/";
		//Step 2 : Configure ResponseBody
		String responseBody=given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}").when().post("/api/users/in").then().extract().response().asString();
		int statusCode=given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}").when().post("/api/users/in").then().extract().statusCode();
		//System.out.println(statusCode);
		//System.out.println(responseBody);
		//Step 3 : Parse the responseBody
		JsonPath jsp= new JsonPath(responseBody);
		String res_name=jsp.getString("name");
		String res_job=jsp.getString("job");
		String res_id=jsp.getString("id");
		String res_createdAt=jsp.getString("createdAt");
		//Step 4 : Validate the response Body
		Assert.assertEquals(statusCode, 201);
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "leader");
		Assert.assertNotNull(res_id);
		String trimming = res_createdAt.substring(0,10);
		System.out.println("Trimming" +trimming);
		String date=LocalDate.now().format(DateTimeFormatter.ISO_DATE).substring(0,10);
		//Assert.assertEquals(res_createdAT.substring(0,10), date);
		Assert.assertEquals(trimming, date);
		System.out.println("date: " +date);
		}    }

		
		
	


