package sampleProj;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
public class DeleteReference {

	public static void main(String[] args) {
		//Step1 : Declare BaseURL
		RestAssured.baseURI="https://reqres.in/";
		
		//Step2: Configure Request Body
		 
		int statusCode=given().header("Content-Type","application/json").when().delete("/api/users/2")
				.then().extract().statusCode();
		System.out.println(statusCode);
	}
	
	}

