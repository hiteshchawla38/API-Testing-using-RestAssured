package LabelRequest;

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
	public static Response Updatelabel(String LabelID, String payload) throws IOException, DocumentException
	{
		Response response = given()
				.pathParam("id",LabelID)
				.contentType("application/json")
				.queryParam("key", XmlReader.ReadfromXml("//data/key"))
				.queryParam("token","ATTA3929f32e7c7ee5208586bd67597a259a4311d529c14c828a87f8176527bd4b3aEFD4AD26")	
				.body(new String(Files.readAllBytes(Paths.get(payload))))
		.when()
			.put(Routes.label_put_url);
			
		return response;
	}
	
	public static Response UpdatelabelField(String LabelID, String field) throws IOException, DocumentException
	{
		Response response = given()
				.pathParam("id",LabelID)
				.queryParam("field", field)
				.contentType("application/json")
				.queryParam("key", XmlReader.ReadfromXml("//data/key"))
				.queryParam("token","ATTA3929f32e7c7ee5208586bd67597a259a4311d529c14c828a87f8176527bd4b3aEFD4AD26")	
		.when()
			.put(Routes.label_put_url);
			
		return response;
	}
}
