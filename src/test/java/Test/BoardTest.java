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

import BoardRequest.DeleteRequest;
import BoardRequest.GetRequest;
import BoardRequest.PostRequest;
import BoardRequest.PutRequest;
import Payload.Board;
import Utilities.XmlReader;
import io.restassured.response.Response;

public class BoardTest {

	String boardTestdata = System.getProperty("user.dir")+ "//TestData//BoardTestData.json";
	Board boardPayload;
	public Logger logger;
	
	@BeforeClass (groups= {"Negative testcases","Sample testcases"})
	public void setup() throws DocumentException
	{
		boardPayload = new Board();	
		boardPayload.setId(XmlReader.ReadfromXml("//data/BoardAPI/Board1/Id"));
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test (priority=1, groups= {"Sample testcases"})
	public void testCreateBoard() throws DocumentException
	{
		logger.info("*********** Creating board **************");
		Response response = PostRequest.createboard();
		response.then().log().all();

		  Assert.assertEquals(response.statusCode(), 200);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  logger.info("Board created");
	}
		
	
	@Test (priority=2)
	public void testReadBoard() throws DocumentException
	{
		logger.info("*********** Reading board **************");
		Response response = GetRequest.readboard(boardPayload.getId());
		response.then().log().all();

		  Assert.assertEquals(response.statusCode(), 200);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
	}
	
	
	  @AfterClass	  
	  @Test public void testDeleteBoard() throws DocumentException
	  {
	  logger.info("*********** Deleting board **************");
	  Response response =
	  DeleteRequest.Deleteboard(boardPayload.getId());
	  response.then().log().all();
	  
	  Assert.assertEquals(response.statusCode(), 200); //Verifying status code
	  logger.info("Status code is :"+response.statusCode());
	  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK"); //Verifying status line 
	  logger.info("Status line is :"+response.statusLine()); long
	  responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS); //Fetching response time 
	  logger.info("Response time is :"+responseTimeInSeconds);
	  logger.info("Board deleted");	  
	  }
	 
	  
	  @Test (priority=3) 
	  public void testUpdateBoardName() throws IOException, DocumentException 
	  {
		  boardPayload.setId(XmlReader.ReadfromXml("//data/BoardAPI/Board2/Id"));
	  
		  Response response;
	  try 
	  { 
		  response = PutRequest.Updateboard(this.boardPayload.getId(),boardTestdata);
		  response.then().log().all();	  
		  
		  Assert.assertEquals(response.statusCode(), 200); //Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK"); //Verifying status line 
		  logger.info("Status line is :"+response.statusLine()); long
		  responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS); //Fetching response time 
		  logger.info("Response time is :"+responseTimeInSeconds);
		  logger.info("Board Name updated");
		  }
	  catch (IOException e) 
	  { 
		  System.out.println(e);
	  }
	 }
	 
	
	@Test (priority=4)
	public void VerifyBoardName() throws DocumentException
	{
		logger.info("*********** Reading board **************");
		Response response = GetRequest.readboard(boardPayload.getId());
		String boardName = response.then().extract().path("name");
		
		  Assert.assertEquals(response.statusCode(), 200);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
		Assert.assertTrue(boardName.equals("Nedbank456"));
		logger.info("Board Name verified");
	}
	
	@Test (priority=5)
	public void VerifyPermissionLevel() throws DocumentException
	{
		logger.info("*********** Reading board **************");
		Response response = GetRequest.readboard(boardPayload.getId());
		String permissionLevel = response.then().extract().path("prefs.permissionLevel");
		
		  Assert.assertEquals(response.statusCode(), 200);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
		Assert.assertTrue(permissionLevel.equals("private"));
		logger.info("Permission Level verified");
	}
	
	@Test (priority=6)
	public void VerifyCalenderView() throws DocumentException
	{
		logger.info("*********** Reading board **************");
		Response response = GetRequest.readboard(boardPayload.getId());
		boolean calendervalue = response.then().extract().path("prefs.switcherViews[2].enabled");
		
		  Assert.assertEquals(response.statusCode(), 200);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
		  Assert.assertEquals(false, calendervalue);
		  logger.info("Calender view verified");		 
	}
	
	@Test (priority=7)
	public void VerifyBackgroundColor() throws DocumentException
	{
		logger.info("*********** Reading board **************");
		Response response = GetRequest.readboard(boardPayload.getId());
		String backgroundColor = response.then().extract().path("prefs.backgroundColor");
		
		  Assert.assertEquals(response.statusCode(), 200);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
		  Assert.assertTrue(backgroundColor.equals("#0079BF"));
		  logger.info("Background color verified");
	}
	
	@Test (groups= {"Negative testcases"})
	public void VerifyInvalidNameMessage() throws DocumentException
	{
		logger.info("*********** Creating board **************");
		Response response = PostRequest.createboardwithBlankName();
		String errorMessage = response.then().extract().path("message");

		  Assert.assertEquals(response.statusCode(), 400);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 400 Bad Request");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
		  Assert.assertTrue(errorMessage.equals("invalid value for name"));
		  logger.info("Testcase passed");
	}
	
	@Test (groups= {"Negative testcases"})
	public void VerifyUnauthorizedAccess() throws DocumentException
	{
		logger.info("*********** Creating board **************");
		Response response = PostRequest.createboardwithInvalidkey();
		String errorMessage = response.then().extract().asString();

		  Assert.assertEquals(response.statusCode(), 401);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 401 Unauthorized");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  
		  Assert.assertTrue(errorMessage.equals("invalid key"));
	}
	
	@Test (groups= {"Negative testcases"})
	public void VerifyNotFoundStatus() throws DocumentException
	{
		logger.info("*********** Creating board **************");
		Response response = GetRequest.readboardInvalidURL(boardPayload.getId());
		String errorMessage = response.then().extract().asString();

		  Assert.assertEquals(response.statusCode(), 404);         		//Verifying status code
		  logger.info("Status code is :"+response.statusCode());
		  Assert.assertEquals(response.statusLine(), "HTTP/1.1 404 Not Found");     //Verifying status line
		  logger.info("Status line is :"+response.statusLine());
		  long responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);    //Fetching response time
		  logger.info("Response time is :"+responseTimeInSeconds);
		  logger.info(errorMessage);
	}
	
}
