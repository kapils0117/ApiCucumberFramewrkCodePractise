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

	public class AddPlace3LogFeature  extends UtilsForCommonCode{
			
				RequestSpecification response1;
				ResponseSpecification respspec;
				Response responsespec2;
		     
				TestData testdata=new TestData();

				@Given("Add Place Payload")
				public void add_place_payload_with(String name, String language, String address) throws IOException {						RestAssured.baseURI="www.rahulshettyacademy.com";
						
		

					//RequestSpecification response1 = given().log().all().spec(requ).body(obj);
					 
					response1 = given().log().all().spec(requestSpecification()).body(testdata.addPlacePayload(name,language,address));

				}
				@When("User calls {string} with POST http request")
				public void user_calls_with_post_http_request(String string) {
					
					 respspec=	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

					 responsespec2= response1.when().post("maps/api/place/add/json").then().log().all().spec(respspec).extract().response();
					
				}
				@Then("the API call got success with status code {int}")
				public void the_api_call_got_success_with_status_code(Integer int1) {
					assertEquals(responsespec2.getStatusCode(),200);
					
				}
				@Then("{string} in response body is {string}")
				public void in_response_body_is(String keyvalue, String keyeExpectedvalue) {
		
					String resp= responsespec2.asString();
			JsonPath js= new JsonPath(resp);
			String s = js.get(keyvalue).toString();
			System.out.println("value is " +s);
			assertEquals(js.get(keyvalue).toString(), keyeExpectedvalue);

				}


			}
			


		



