package sampleProj;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
public class getnewURL {

	public static void main(String[] args) {
		//Declare baseURL
		String baseURI="https://dummy.restapiexample.com/";
		//Configure API
		RestAssured.baseURI=baseURI;
		//Declare const results
		String responsebody=given().header("Content-Type","application/json").when().
				get("/api/v1/employees").then().extract().response().asString();
		System.out.println(responsebody);
		
		int id[]= {1};  
		String employee_name []= {"Tiger Nixon"};
		
		//Parse
		JsonPath jsp=new JsonPath(responsebody);
		int count = jsp.getList("data").size();
		System.out.println(count);
		for (int i=0; i<count ;i++)
		{
			//fetch results
			int exp_id=id[i];
			String exp_employee_name=employee_name[i];
			
			String res_id=jsp.getString("data["+i+"].id");
			int res_int_id=Integer.parseInt(res_id);
			String res_employee_name=jsp.getString("data["+i+"].employee_name");
			
			//validate
			Assert.assertEquals(res_id, exp_id ,"id at index" +i);
			Assert.assertEquals(res_employee_name, exp_employee_name, "employee_name at index" +i);
				}
	} 
}

