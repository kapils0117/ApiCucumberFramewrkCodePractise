package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.LocationPojo17;
import pojo.PojoForClass17;
import resourcesTestData.TestData;
import resourcesTestData.UtilsForCommonCode;

//Step def file should have only core login not the test data hardcoded, it should supply from other class by calling java methods 

public class OptimizedTestDataAddPlace2  extends UtilsForCommonCode{
//Here in this class we're not providing test data hard coded as we did in AddPlace1 class, here we're supplying testdata by another class object and calling it here.
	
	
		RequestSpecification response1;
		ResponseSpecification respspec;
		Response responsespec2;
     
		 //Here we called Testdata class payload method and also we created TestData class object first here above Given annotation to call method by object
		TestData testdata=new TestData();

//		@Given("Add Place Payload")
//		public void add_place_payload() throws IOException {
			@Given("Add Place Payload with {string} {string} {string}")
			public void add_place_payload_with(String name, String language, String address) throws IOException {
				
// WE can supply below common/ generic code from other UTIL class as This below code api uri, query param and content type are common for three api's like add place , get, update and delete so we will extend this class by UTIL class
	//RequestSpecification requ=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				//.setContentType(ContentType.JSON).build();
			//This method (ResponseSpecification respspec) need to use in other methods as well so we've to declare it at global level outside all methods
			// so below we're removing ResponseSpecification as we already declared it at global level/class level : 

	//This method (RequestSpecification response1) need to use in other methods as well so we've to declare it at global level outside all methods
	// so below we're removing Requestspecification as we already declared it at global level/class level 
			//RequestSpecification response1 = given().log().all().spec(requ).body(obj);
			 
			 //Here we called Testdata class payload method and also we created TestData class object first here above Given annotation to call method by object
//			Here we're providing 3 arguments which we declared in feature file
				response1 = given().spec(requestSpecification())
					.body(testdata.addPlacePayload(name, language, address));
			//Above in .spec(requestSpecification()) reqspec is method name from UTIL class and this class extends UTL class to use this method and inside body we're passing calling this method from another class by creating object here

		}
		@When("User calls {string} with POST http request")
		public void user_calls_with_post_http_request(String string) {
		   // Here we will write POST method code only means when() keyword code where we send the POST request 
			 
			 respspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			responsespec2= response1.when().post("maps/api/place/add/json").then().log().all().spec(respspec).extract().response();
	//Declare above variable globally
			
		}
		@Then("the API call got success with status code {int}")
		public void the_api_call_got_success_with_status_code(Integer int1) {
			assertEquals(responsespec2.getStatusCode(),200);
			
		}
		@Then("{string} in response body is {string}")
		public void in_response_body_is(String keyvalue, String keyeExpectedvalue) {
		 // To get the "status" code as "OK" & "scope" as "APP" we need to covert JSON to String and then use JSONPath method to fetch the response params values
	//Also here we've 2 variables as per features file so in one variable "status" will be set and fetched value and in other var(keyeExpectedvalue) OK will be set as per feature file then we can compare 	
	//In feature file definition is same for both variables "status" and "scope" so we dont need to create another method for these, same method will work and both values fall here in variables / arguments one by one
			String resp= responsespec2.asString();
	JsonPath js= new JsonPath(resp);
	String s = js.get(keyvalue).toString();
	System.out.println("value is " +s);
	assertEquals(js.get(keyvalue).toString(), keyeExpectedvalue);

		}


	}
	

