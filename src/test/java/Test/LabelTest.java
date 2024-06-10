package Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import LabelRequest.*;
import Payload.Label;
import Utilities.XmlReader;
import io.restassured.response.Response;

public class LabelTest {

	String labelTestdata = System.getProperty("user.dir")+ "//TestData//UpdateLabelTestData.json";    //Fetching and storing label test data JSON file
	Label labelPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup() throws DocumentException
	{
		labelPayload = new Label();	
		labelPayload.setName(XmlReader.ReadfromXml("//data/LabelAPI/Name"));          //setting the label name from Xml file
		labelPayload.setColor(XmlReader.ReadfromXml("//data/LabelAPI/Color"));			//setting the label color from Xml file
		labelPayload.setLabelid(XmlReader.ReadfromXml("//data/LabelAPI/LabelId"));		//setting the label ID from Xml file
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test (priority=1)
	public void testCreateLabel() throws DocumentException
	{
		logger.info("*********** Creating Label **************");
		Response response = PostRequest.createlabel();
		response.then().log().all();

		  Assert.assertEquals(response.statusCode(), 200);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  logger.info("Label created");
	}
		
	
	@Test (priority=2)
	public void testReadLabel() throws DocumentException
	{
		logger.info("*********** Creating Label **************");
		Response postresponse = PostRequest.createlabel();
		postresponse.then().log().all();
		String LabelID = postresponse.then().extract().path("id");          //Storing label ID
		
		logger.info("*********** Reading Label **************");
		Response response = GetRequest.readlabel(LabelID);            //Retrieving the response with the same label ID from above
		response.then().log().all();

		  Assert.assertEquals(response.statusCode(), 200);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
	}	
	@Test (priority=3)
	public void VerifyLabelName() throws DocumentException
	{
		logger.info("*********** Creating Label **************");
		Response postresponse = PostRequest.createlabel();
		postresponse.then().log().all();
		String LabelID = postresponse.then().extract().path("id");          //Storing label ID
		
		logger.info("*********** Reading label **************");
		Response response = GetRequest.readlabel(LabelID);
		String labelName = response.then().extract().path("name");
		
		  Assert.assertEquals(response.statusCode(), 200);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
		Assert.assertTrue(labelName.equals("Loan"));
		logger.info("Label Name verified");
	}
	
	@Test (priority=4)
	public void VerifyColorName() throws DocumentException
	{
		logger.info("*********** Creating Label **************");
		Response postresponse = PostRequest.createlabel();
		postresponse.then().log().all();
		String LabelID = postresponse.then().extract().path("id");          //Storing label ID
		
		logger.info("*********** Reading label **************");
		Response response = GetRequest.readlabel(LabelID);
		String colorName = response.then().extract().path("color");
		
		  Assert.assertEquals(response.statusCode(), 200);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
		  Assert.assertTrue(colorName.equals("red"));
		  logger.info("color name verified");
	}
	
	  @AfterClass 
	  public void testDeleteLabel() throws DocumentException 
	  {
			logger.info("*********** Creating Label **************");
			Response postresponse = PostRequest.createlabel();
			postresponse.then().log().all();
			String LabelID = postresponse.then().extract().path("id");          //Storing label ID
			
			logger.info("*********** Deleting label **************");
			Response response = DeleteRequest.Deletelabel(LabelID);
			response.then().log().all();
	  
	  Assert.assertEquals(response.statusCode(), 200); 						//Verifying status code
	  logger.info("Status code is :"+response.statusCode());
	  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK"); 		//Verifying  status line 
	  logger.info("Status line is :"+response.statusLine()); long
	  responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS); 		//Fetching  response time 
	  logger.info("Response time is :"+responseTimeInSeconds);
	  logger.info("Label deleted");
	  
	  }
	 
	  
	  @Test (priority=5) 
	  public void testUpdateLabel() throws IOException, DocumentException 
	  {
		  Response response;
	  try 
	  { 
			logger.info("*********** Creating Label **************");
			Response postresponse = PostRequest.createlabel();
			postresponse.then().log().all();
			String LabelID = postresponse.then().extract().path("id");          //Storing label ID
			
			response = PutRequest.Updatelabel(LabelID,labelTestdata);	  
		  Assert.assertEquals(response.statusCode(), 200);
	  } 
	  catch (IOException e) 
	  { 
		  System.out.println(e);
	  }
	  }

	  
	  @Test (priority=6) 
	  public void testUpdateLabelName() throws IOException, DocumentException
	  {
		logger.info("*********** Creating Label **************");
		Response postresponse = PostRequest.createlabel();
		postresponse.then().log().all();
		String LabelID = postresponse.then().extract().path("id");          //Storing label ID
		 
		labelPayload.setName("PersonalLoan");
		Response response = PutRequest.UpdatelabelField(LabelID, labelPayload.getName());
		response.then().log().all();
		  
		  Assert.assertEquals(response.statusCode(), 200);         				//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     	//Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
	  }
	  
	  @Test (priority=7) 
	  public void testUpdateLabelColor() throws IOException, DocumentException
	  {
		logger.info("*********** Creating Label **************");
		Response postresponse = PostRequest.createlabel();
		postresponse.then().log().all();
		String LabelID = postresponse.then().extract().path("id");          //Storing label ID
	
		labelPayload.setColor("orange");  
		Response response = PutRequest.UpdatelabelField(LabelID, labelPayload.getColor());
		response.then().log().all();
		  
		  Assert.assertEquals(response.statusCode(), 200);         				//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     	//Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
	  }
}