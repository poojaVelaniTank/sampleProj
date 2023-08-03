package sampleProj;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
public class PutRef {
	public static void main(String[] args) {
		//Step1 : Declare BaseURI
		RestAssured.baseURI="https://reqres.in/";
		//Configure requestbody
		int statusCode=given().header("COntent-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").when().put("/api/users/2").then().extract().statusCode();
		String responsebody=given().header("COntent-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").when().put("/api/users/2").then().extract().response().asString();
		System.out.println( statusCode);
		System.out.println(responsebody);
		//Parse the responsebody
		JsonPath jsp=new JsonPath(responsebody);
		String res_name=jsp.getString("name");
		String res_job=jsp.getString("job");
		String res_id=jsp.getString("id");
		String res_updatedAt=jsp.getString("updatedAt");
		//Validate the responsebody
		Assert.assertEquals(res_name,"morpheus");
		Assert.assertEquals(res_job, "leader");
		Assert.assertNotNull(res_id);
		String Trimming=res_updatedAt.substring(0,10);
		System.out.println("Trimming" +Trimming);
		String date=LocalDate.now().format(DateTimeFormatter.ISO_DATE).substring(0,10);
		Assert.assertEquals(Trimming, date);
	}       }

	


