package BoardRequest;

import static io.restassured.RestAssured.given;

import org.dom4j.DocumentException;

import Endpoints.Routes;
import Utilities.XmlReader;
import io.restassured.response.Response;

public class PostRequest {

	
	public static Response createboard() throws DocumentException
	{
		Response response = given()
			.contentType("application/json")
			.queryParam("name", XmlReader.ReadfromXml("//data/BoardName"))
			.queryParam("key", XmlReader.ReadfromXml("//data/key"))
			.queryParam("token",XmlReader.ReadfromXml("//data/token"))
		.when()
			.post(Routes.board_post_url);
			
		return response;
	}
	
	public static Response createboardwithBlankName() throws DocumentException
	{
		Response response = given()
			.contentType("application/json")
			.queryParam("name", " ")
			.queryParam("key", XmlReader.ReadfromXml("//data/key"))
			.queryParam("token",XmlReader.ReadfromXml("//data/token"))
		.when()
			.post(Routes.board_post_url);
			
		return response;
	}
	
	public static Response createboardwithInvalidkey() throws DocumentException
	{
		Response response = given()
			.contentType("application/json")
			.queryParam("name", " ")
			.queryParam("key", "123")
			.queryParam("token","123")
		.when()
			.post(Routes.board_post_url);
			
		return response;
	}
}
