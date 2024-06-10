package BoardRequest;

import static io.restassured.RestAssured.given;

import org.dom4j.DocumentException;

import Endpoints.Routes;
import Utilities.XmlReader;
import io.restassured.response.Response;

public class GetRequest {

	public static Response readboard(String BoardID) throws DocumentException
	{
		Response response = given()
				.pathParam("id",BoardID)
				.contentType("application/json")
				.queryParam("key", XmlReader.ReadfromXml("//data/key"))				 //Reading key from XML file
				.queryParam("token",XmlReader.ReadfromXml("//data/token"))				 //Reading token from XML file
		.when()
			.get(Routes.board_get_url);
			
		return response;
	}
	
	public static Response readboardInvalidURL(String BoardID) throws DocumentException
	{
		Response response = given()
				.pathParam("id",BoardID)
				.contentType("application/json")
				.queryParam("key", XmlReader.ReadfromXml("//data/key"))				 //Reading key from XML file
				.queryParam("token",XmlReader.ReadfromXml("//data/token"))				 //Reading token from XML file
		.when()
			.get(Routes.board_Invalid_url);
			
		return response;
	}
}
