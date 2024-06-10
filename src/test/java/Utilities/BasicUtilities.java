package Utilities;

import java.util.Base64;

import org.dom4j.DocumentException;
import org.testng.annotations.Test;

public class BasicUtilities {

	
	public static String DecodeString(String encodedString) throws DocumentException
	{
  
        // Creating Base64 encoder and decoder instances 
        Base64.Decoder decoder = Base64.getDecoder(); 
  
        // Decoding the Base64 encoded string 
        byte[] decodedBytes = decoder.decode(encodedString); 
        String decodedString = new String(decodedBytes); 

        return decodedString;
	}
}
