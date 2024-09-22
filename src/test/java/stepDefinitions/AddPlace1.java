/*
 * package stepDefinitions;
 * 
 * import static io.restassured.RestAssured.given; import static
 * org.junit.Assert.*;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import io.cucumber.java.en.Given; import io.cucumber.java.en.Then; import
 * io.cucumber.java.en.When; import io.restassured.RestAssured; import
 * io.restassured.builder.RequestSpecBuilder; import
 * io.restassured.builder.ResponseSpecBuilder; import
 * io.restassured.http.ContentType; import io.restassured.path.json.JsonPath;
 * import io.restassured.response.Response; import
 * io.restassured.specification.RequestSpecification; import
 * io.restassured.specification.ResponseSpecification; import
 * pojo.LocationPojo17; import pojo.PojoForClass17;
 * 
 * public class AddPlace1 { RequestSpecification response1;
 * ResponseSpecification respspec; Response responsespec2;
 * 
 * @Given("Add Place Payload") public void add_place_payload() {
 * RestAssured.baseURI="www.rahulshettyacademy.com"; // we can also supply this url from other class
 
 * PojoForClass17 obj = new PojoForClass17();//here we've to create object of parent pojo class to call 8 setter methods here 
 * obj.setAccuracy(55);
 * obj.setAddress("K-Street Polly Hill wonder445"); obj.setLanguage("Hindi");
 * obj.setName("Rahul"); obj.setPhone_number("1234567890");
 * obj.setWebsite("www.rahulshettyacademy.com");
 * 
 * List<String> types2=new ArrayList<String>(); types2.add("shoe park");
 * types2.add("shop"); 
 * obj.setTypes(types2);
 * 
 * LocationPojo17 obj2= new LocationPojo17();// here we've to create object of Location pojo class to call setter methods here obj2.setLat(-12);
  obj2.setLng(45); 
  obj.setLocation(obj2);// here we're passing obj of LocationPojo17 class and the object "obj2" has values we provided
 
// WE can supply below common/ generic code from other UTIL class as This below code api uri, query param and content type are common for three api's like add place , get, update and delete
 RequestSpecification requ=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
 addQueryParam("key", "qaclick123") .setContentType(ContentType.JSON).build(); //This method (ResponseSpecification respspec) need to use in other methods as well so we've to declare it at global level outside all methods // so
 below we're removing ResponseSpecification as we already declared it at global level/class level : 
 
  //RequestSpecification response1 = given().log().all().spec(requ).body(obj); 
   * response1 = given().log().all().spec(requ).body(obj);
 
 }
  @When("User calls {string} with POST http request") 
  public void user_calls_with_post_http_request(String string) { // Here we will write POST method code only means when() keyword code where we send the POST request
 respspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build(); //This method (RequestSpecification response1) need to use in other methods as well so we've to declare it at global level outside all methods 
 // so below we're removing Requestspecification as we already declared it at global level/class level 
 
 responsespec2=response1.when().post("maps/api/place/add/json").then().log().all().spec(respspec).extract().response(); //Declare above variable globally
 }
 @Then("the API call got success with status code {int}") 
 public void the_api_call_got_success_with_status_code(Integer int1) {
 * assertEquals(responsespec2.getStatusCode(),200);
 }
 @Then("{string} in response body is {string}") 
 public void in_response_body_is(String keyvalue, String keyeExpectedvalue) { // To get the "status" code as "OK" & "scope" as "APP" we need to covert JSON to String and then use JSONPath method to fetch the response params values //Also here
 //we've 2 variables as per features file so in one variable "status" will be set and fetched value and in other var(keyeExpectedvalue) OK will be set as per feature file then we can compare //In feature file definition is same for
 // both variables "status" and "scope" so we dont need to create another method for these, same method will work and both values fall here in variables / arguments one by one String resp= responsespec2.asString(); JsonPath js= new
 
 JsonPath(resp); String s = js.get(keyvalue).toString();
 System.out.println("value is " +s); assertEquals(js.get(keyvalue).toString(),keyeExpectedvalue);
  }
  }
 */