package BoardRequest;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.dom4j.DocumentException;

import Endpoints.Routes;
import Payload.Board;
import Utilities.XmlReader;
import io.restassured.response.Response;

public class PutRequest {
	public static Response Updateboard(String BoardID, String payload) throws IOException, DocumentException
	{
		Response response = given()
				.pathParam("id",BoardID)
				.contentType("application/json")
				.queryParam("key", XmlReader.ReadfromXml("//data/key"))
				.queryParam("token",XmlReader.ReadfromXml("//data/token"))
				.body(new String(Files.readAllBytes(Paths.get(payload))))
		.when()
			.put(Routes.board_put_url);
			
		return response;
	}
}
