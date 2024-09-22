Feature: Validating place API's

#Scenario: Verify if place is being successfully added using AddPlaceAPI
 #   Given Add Place Payload
 #   When User calls "AddPlaceAPI" with POST http request
  #  Then the API call got success with status code 200
   # And "status" in response body is "OK"
  #  And "scope" in response body is "APP"

# Here we're managing 3 params i.e name, language and address dynamically so in step definition method, test data method we need to declare 3 arguments
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with POST http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    
Examples:
    |name        |language        address        |
    |Kapil       |Hindi      Saket-Pandav Nagar  |       
