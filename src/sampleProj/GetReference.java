package sampleProj;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
public class GetReference {
	public static void main(String[] args) {
		//Step1: Declare BaseURL
		RestAssured.baseURI="https://reqres.in/";
		//Step2: Configure requestbody
		int satusCode=given().header("Content-Type","application/json").when().get("/api/users?page=2").
			then().extract().statusCode();
		String responsebody=given().header("Content-Type","application/json").when().get("/api/users?page=2").
				then().extract().response().asString();
		System.out.println(satusCode);
		System.out.println(responsebody);
		
		int id []= {7,8,9,10,11,12};
		String [] email= {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in",
				"byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"};
		String [] firstname= {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
		String [] lastname= {"Lawson","Ferguson","Funke","Fields","Edwards","Howell"};
		
		JsonPath jsp=new JsonPath(responsebody);
		int count=jsp.getList("data").size();
		System.out.println(count);
		
		//Validate each object in dataArray
		for(int i=0; i<count ; i++)
		{
			//Fetch responsebody
			int exp_id=id[i];
			String exp_email=email[i];
			String exp_firstname=firstname[i];
			String exp_lastname=lastname[i];
			
			String res_firstname=jsp.getString("data["+i+"].firstname");
			String res_lastname=jsp.getString("data["+i+"].lastname");
			String res_email=jsp.getString("data["+i+"].email");
			String res_id=jsp.getString("data["+i+"].id");
			int res_int_id=Integer.parseInt(res_id);
			
	Assert.assertEquals(res_email,  exp_email, "email at index" +i);		
	Assert.assertEquals(res_firstname, exp_firstname, "firstname at index" +i);
	Assert.assertEquals(res_lastname, exp_lastname, "lastname at index" +i);
	Assert.assertEquals(res_int_id, exp_id ,"id at index" +i);
		}         
	}
	}		
				
		
		
		
		
		
		
		
	


