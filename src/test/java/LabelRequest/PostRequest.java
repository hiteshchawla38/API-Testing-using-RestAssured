package LabelRequest;

import static io.restassured.RestAssured.given;

import org.dom4j.DocumentException;

import Endpoints.Routes;
import Utilities.BasicUtilities;
import Utilities.XmlReader;
import io.restassured.response.Response;

public class PostRequest {

	
	public static Response createlabel() throws DocumentException
	{
		Response response = given()
			.contentType("application/json")
			.queryParam("name", XmlReader.ReadfromXml("//data/LabelAPI/Name"))
			.queryParam("color", XmlReader.ReadfromXml("//data/LabelAPI/Color"))
			.queryParam("idBoard", XmlReader.ReadfromXml("//data/LabelAPI/BoardId"))
			.queryParam("key", XmlReader.ReadfromXml("//data/key"))
			.queryParam("token",BasicUtilities.DecodeString(XmlReader.ReadfromXml("//data/token")))	
		.when()
			.post(Routes.label_post_url);
			
		return response;
	}
	
	public static Response createlabelwithBlankName() throws DocumentException
	{
		Response response = given()
				.contentType("application/json")
				.queryParam("name", " ")
				.queryParam("color", XmlReader.ReadfromXml("//data/LabelAPI/Color"))
				.queryParam("idBoard", XmlReader.ReadfromXml("//data/LabelAPI/BoardId"))
				.queryParam("key", XmlReader.ReadfromXml("//data/key"))
				.queryParam("token",BasicUtilities.DecodeString(XmlReader.ReadfromXml("//data/token")))	
		.when()
			.post(Routes.label_post_url);
			
		return response;
	}
	
	public static Response createlabelwithInvalidkey() throws DocumentException
	{
		Response response = given()
				.contentType("application/json")
				.queryParam("name", XmlReader.ReadfromXml("//data/LabelAPI/Name"))
				.queryParam("color", XmlReader.ReadfromXml("//data/LabelAPI/Color"))
				.queryParam("idBoard", XmlReader.ReadfromXml("//data/LabelAPI/BoardId"))
				.queryParam("key", "123")
				.queryParam("token","123")
		.when()
			.post(Routes.label_post_url);
			
		return response;
	}
}
