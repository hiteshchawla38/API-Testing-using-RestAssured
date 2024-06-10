package Utilities;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.testng.annotations.Test;

public class XmlReader {

	@Test
	public static String ReadfromXml(String path) throws DocumentException
	{
		File inputFile = new File(System.getProperty("user.dir") +"//TestData//TestData.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputFile);
        String data = document.selectSingleNode(path).getText();
        return data;
	}
}
