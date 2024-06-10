package BoardRequest;

import static io.restassured.RestAssured.given;

import org.dom4j.DocumentException;

import Endpoints.Routes;
import Utilities.XmlReader;
import io.restassured.response.Response;

public class DeleteRequest {

	public static Response Deleteboard(String BoardID) throws DocumentException
	{
		Response response = given()
				.pathParam("id",BoardID)
				.contentType("application/json")
				.queryParam("key", XmlReader.ReadfromXml("//data/key"))          //Reading key from XML file
				.queryParam("token","ATTA3929f32e7c7ee5208586bd67597a259a4311d529c14c828a87f8176527bd4b3aEFD4AD26")		//Reading token from XML file
		.when()
			.delete(Routes.board_delete_url);
			
		return response;
	}
}
