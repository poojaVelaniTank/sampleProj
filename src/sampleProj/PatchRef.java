package sampleProj;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
public class PatchRef {
	public static void main(String[] args) {
	//Step 1 : Declare baseURI
	RestAssured.baseURI="https://reqres.in/";
	//Step 2: Configure requestbody
	int statusCode=given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").when().patch("/api/users/2").then().extract().statusCode();
	String responsebody= given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").when().patch("/api/users/2").then().extract().response().asString();
	System.out.println(statusCode);
	System.out.println(responsebody);
	//Step 3 : Parse the responsebody
	JsonPath jsp=new JsonPath(responsebody);
	String res_name = jsp.getString("name");
	String res_job=jsp.getString("job");
	String res_id=jsp.getString("id");
	String res_createdAt=jsp.getString("createdAt");
	//Step 4 : Validate the responsebody
	Assert.assertEquals(statusCode, 200);
	Assert.assertEquals(res_name, "morpheus");
	Assert.assertEquals(res_job, "leader");
	//Assert.assertNotNull(res_id);
	String trimming=res_createdAt.substring(0,10);
	System.out.println("Trimming" +trimming);
	String date=LocalDate.now().format(DateTimeFormatter.ISO_DATE).substring(0,10);
	Assert.assertEquals(trimming, date);
	System.out.println("date:" +date);
	}  }


