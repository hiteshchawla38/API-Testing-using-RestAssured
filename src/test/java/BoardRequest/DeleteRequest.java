package BoardRequest;

import static io.restassured.RestAssured.given;

import org.dom4j.DocumentException;

import Endpoints.Routes;
import Utilities.BasicUtilities;
import Utilities.XmlReader;
import io.restassured.response.Response;

public class DeleteRequest {

	public static Response Deleteboard(String BoardID) throws DocumentException
	{
		Response response = given()
				.pathParam("id",BoardID)
				.contentType("application/json")
				.queryParam("key", XmlReader.ReadfromXml("//data/key"))          //Reading key from XML file
				.queryParam("token",BasicUtilities.DecodeString(XmlReader.ReadfromXml("//data/token")))			
		.when()
			.delete(Routes.board_delete_url);
			
		return response;
	}
}
