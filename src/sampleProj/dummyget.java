package sampleProj;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
public class dummyget {

	public static void main(String[] args) {
		String baseURI="https://petstore.swagger.io";
		RestAssured.baseURI=baseURI;
	String responsebody = given().header("Content-Type","application/json").body("").when()
	.get("/v2/pet/findByStatus?status=available").then().extract().response().asString();
	System.out.println(responsebody);
	
	//Declare const
	String name [] = {"string","fish"};
	
	JsonPath jsp= new JsonPath(responsebody);
	int count=jsp.getList("data").size();
	System.out.println(count);
	
	for(int i=0; i<count ;i++)
	{
	   //fetch results
		String exp_name=name[i];
		
		String res_name=jsp.getString("data["+i+"].name");
		
		Assert.assertEquals("res_name","exp_name", "name at index" +i);
	}
}
	}
