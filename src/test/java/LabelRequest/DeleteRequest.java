package LabelRequest;

import static io.restassured.RestAssured.given;

import org.dom4j.DocumentException;

import Endpoints.Routes;
import Utilities.BasicUtilities;
import Utilities.XmlReader;
import io.restassured.response.Response;

public class DeleteRequest {

	public static Response Deletelabel(String LabelID) throws DocumentException
	{
		Response response = given()
				.pathParam("id",LabelID)
				.contentType("application/json")
				.queryParam("key", XmlReader.ReadfromXml("//data/key"))
				.queryParam("token",BasicUtilities.DecodeString(XmlReader.ReadfromXml("//data/token")))	
		.when()
			.delete(Routes.label_delete_url);
			
		return response;
	}
}
