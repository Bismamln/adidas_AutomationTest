@APITest
Feature: API Test for Pets in the Store


Background: Entering the API call
	Given User enters the Pet store using API call
	
@GetRequest
Scenario: Make Get Request to test the Available pets in the store
	When User makes Get request for available pets in the store
	Then User should get the availability status of pets in the store
	

@PostRequest	
Scenario: Make POST Request to add the new Pet to the store
	When User makes a POST request to add the new pet to the store
	Then User should be able add the new pet and should get the success code

@PutRequest
Scenario: Make PUT Request to update the newly added Pet to the store
	When User makes a PUT request to update the newly added pet to the store
	Then User should be able update the pet status to sold and should get the success code
	
@DeleteRequest
Scenario: Make Delete Request to delete Pet from the store
	When User makes a Delete request to delete the pet from the store
	Then User should be able delete the pet and should get the success code