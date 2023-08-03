package sampleProj;
import io.restassured.path.xml.XmlPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
public class sssssoappppp {
		public static void main(String[] args) {
		//Declare baseURL and requestbody
		String baseURI = "https://www.dataaccess.com";
		String requestbody="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		System.out.println(requestbody);
		RestAssured.baseURI= baseURI;
		
		//COnfigure API
		int statuscode=  given().header("Content-Type","text/xml; charset=utf-8").body(requestbody).when().
	post("webservicesserver/NumberConversion.wso").then().extract().statusCode();				
       String responsebody= given().header("COntent-Type","text/xml; charset=utf-8").body(requestbody).when().
   post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
       System.out.println(statuscode);
       System.out.println(responsebody);
       
       XmlPath xmlreq = new XmlPath(requestbody);
       String req_name = xmlreq.getString("ubiNum");
       System.out.println(req_name);
       
       XmlPath xml = new XmlPath(responsebody);
       String result = xml.getString("responsebody");
       System.out.println(result);
       
       Assert.assertEquals(result, "one hundred ");
	}
}
