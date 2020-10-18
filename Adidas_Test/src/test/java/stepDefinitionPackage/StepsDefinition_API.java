package stepDefinitionPackage;


import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class StepsDefinition_API {
	private RequestSpecification httpRequest ;
	private Response response ;
	private ResponseBody responseBody ;
	private String actualPetStatus = "available";
	private int actualStatusCode , expectedStatusCode ;
	String expectedPetStatus ;
	int id = 1111219;

	@Given("^User enters the Pet store using API call$")
	public void user_enters_the_pet_store_using_api_call() throws Throwable {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";

		httpRequest = RestAssured.given();
	}

	@When("^User makes Get request for available pets in the store$")
	public void user_makes_get_request_for_available_pets_in_the_store() throws Throwable{

		response = httpRequest.request(Method.GET ,"/pet/findByStatus?status=available");
	}

	@Then("^User should get the availability status of pets in the store$")
	public void user_should_get_the_availability_status_of_pets_in_the_store() throws Throwable{
		actualStatusCode = 200 ;
		expectedStatusCode = response.getStatusCode();
		if(actualStatusCode == expectedStatusCode) {
			responseBody = response.getBody();
			expectedPetStatus = responseBody.asString();
			Assert.assertEquals(expectedPetStatus.toLowerCase().contains(actualPetStatus),true,"Response Body Contains Pet status as Available");
		}

	}

	@When("^User makes a POST request to add the new pet to the store$")
	public void user_makes_a_POST_request_to_add_the_new_pet_to_the_store() throws Throwable {
		JSONObject params = new JSONObject();

		params.put("id", id);
		params.put("name", "rest1218");
		params.put("status", "available");
		//params.put("photoUrls", "https://media.karousell.com/media/photos/products/2017/09/14/doggi_door_stopper_1505372529_5cdd1eba0");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(params.toJSONString());
		response = httpRequest.post("/pet");
	}

	@Then("^User should be able add the new pet and should get the success code$")
	public void user_should_be_able_add_the_new_pet_and_should_get_the_success_code() throws Throwable {
		actualStatusCode = 200 ;
		expectedStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode,expectedStatusCode,"New Pet has not been added to the Store");
		}

	
	@When("^User makes a PUT request to update the newly added pet to the store$")
	public void user_makes_a_PUT_request_to_update_the_newly_added_pet_to_the_store() throws Throwable {
		JSONObject params = new JSONObject();
		
		//params.put("name", "rest1212");
		params.put("status", "sold");
		//params.put("photoUrls", "https://media.karousell.com/media/photos/products/2017/09/14/doggi_door_stopper_1505372529_5cdd1eba0");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(params.toJSONString());
		response = httpRequest.put("/pet/" + id);
	}
	
	@Then("^User should be able update the pet status to sold and should get the success code$")
	public void user_should_be_able_update_the_pet_status_to_sold_and_should_get_the_success_code() throws Throwable {
		actualStatusCode = 200 ;
		expectedStatusCode = response.getStatusCode();
		System.out.println(expectedStatusCode);
		Assert.assertEquals(actualStatusCode,expectedStatusCode,"Pet has not been updated to the Store");
		}
	
	@When("^User makes a Delete request to delete the pet from the store$")
	public void user_makes_a_Delete_request_to_delete_the_pet_from_the_store() throws Throwable {
		JSONObject params = new JSONObject();
				
		//params.put("name", "rest1212");
		//params.put("photoUrls", "https://media.karousell.com/media/photos/products/2017/09/14/doggi_door_stopper_1505372529_5cdd1eba0");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(params.toJSONString());
		response = httpRequest.delete("/pet/" + id);
	}
	
	@Then("^User should be able delete the pet and should get the success code$")
	public void user_should_be_able_delete_the_pet_and_should_get_the_success_code() throws Throwable {
		actualStatusCode = 200 ;
		expectedStatusCode = response.getStatusCode();
		System.out.println(expectedStatusCode);
		Assert.assertEquals(actualStatusCode,expectedStatusCode,"Pet has not been deleted from the Store");
		}



}
