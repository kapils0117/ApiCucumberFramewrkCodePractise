package resourcesTestData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.stream.Stream;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class UtilsForCommonCode {
//In this class we will write generic/ common & Reusable code which can be used by different API'S like 
	RequestSpecification requ;
	public RequestSpecification requestSpecification() throws IOException {
		
		//Creating object of Stream class, here one new file will be created automatically and will store all logs and print request and response
		//Need to refresh the project after run to see new log file
		//FileOutputStream fo=new FileOutputStream("loggin.txt");
		//PrintStream ps=new PrintStream(fo);
		PrintStream ps=new PrintStream(new FileOutputStream("loggin.txt"));
		//We're calling static method "getGlobalValue" below & providing one arg "baseuri" so by this way we're preventing to provide any hardcoded key from property file
		
		requ=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("key", "qaclick123")
//here we're calling LOGGIN method and it will log everything request and response
				 .addFilter(RequestLoggingFilter.logRequestTo(ps))
				 .addFilter(ResponseLoggingFilter.logResponseTo(ps))
				 .setContentType(ContentType.JSON).build();
		 return requ;
	}
	public static String getGlobalValue(String key) throws IOException {
		Properties prop=new Properties();
	//Properties method can read/scan any value from property file
		//FileInputStream is used to read the file
	FileInputStream fi=new FileInputStream("C:\\Users\\KSHARM23\\eclipse-workspace\\APIFramework\\src\\test\\java\\resourcesTestData\\global.properties");
	prop.load(fi);         //Used to load the file
	return prop.getProperty(key);
	
	
	}
	
	
}
