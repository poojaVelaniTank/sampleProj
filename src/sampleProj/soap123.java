package sampleProj;
import io.restassured.path.xml.XmlPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
public class soap123 {
    public static void main(String[] args) {
		//Declare baseURl
		String baseURI="https://www.dataaccess.com";
		String requestbody="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n";
		//configure
		RestAssured.baseURI=baseURI;
		//Configure
		String responsebody=given().header("Content-Type","text/xml; charset=utf-8").body(requestbody).
		when().post("webservicesserver/NumberConversion.wso").then().extract().response().
		asString();
		System.out.println(responsebody);
		
		//Parse requestbody
		XmlPath req= new XmlPath(requestbody);
		String req_param=req.getString("ubiNum");
		System.out.println(req_param);
		
		//Parse responsebody
		XmlPath xml=new XmlPath(responsebody);
		String result=xml.getString("responsebody");
		System.out.println(result);
		
		//validate
		Assert.assertEquals(result, "one hundred ");
		}
}

