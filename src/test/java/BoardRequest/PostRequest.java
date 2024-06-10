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
			.queryParam("token","ATTA3929f32e7c7ee5208586bd67597a259a4311d529c14c828a87f8176527bd4b3aEFD4AD26")	
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
			.queryParam("token","ATTA3929f32e7c7ee5208586bd67597a259a4311d529c14c828a87f8176527bd4b3aEFD4AD26")	
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
