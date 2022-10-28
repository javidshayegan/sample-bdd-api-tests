@APITest
Feature: Api sample test: Validate Place API

  Background: setting up the URI
    Given I set up the baseURI for the API

  Scenario: GET request is successful
    When I create "GET" request for the@ endpoint "GETexampleApiEndpoint"
    And I execute API request
    Then I check success statusCode of "200 OK"
    And I save the endpoint response to JsonACTUAL file

  Scenario: GET request is successful
    When I create "GET" request for the@ endpoint "GETexampleApiEndpoint/"
    And I had the below Query Parameters to the request
      | Keys    | Values |
      | Country | UK     |
      | Capital | London |
    And I execute API request
    Then I check success statusCode of "200 OK"

  Scenario: GET request return 404 or Not foundIt s
    When I create "GET" request for the@ endpoint "1233"
    And I execute API request
    Then I check success statusCode of "401 Not Found"

  Scenario: Get Request returning 401 unAuthorized
    When I create "GET" request for the@ endpoint "GETexampleApiEndpoint"
    And I execute API request
    Then I check success statusCode of "401 Not Found"

  Scenario: POST Request Created Successful
    When I create "POST" request for the@ endpoint "POSTexampleApiEndpoint"
    And I add the json body "example.json" to the request
    And I execute API request
    Then I check success statusCode of "201 created"

  Scenario: Post Request returning 400 bad request
    When I create "POST" request for the@ endpoint "POSTExampleApiEndpoint"
    And I add the json body "example.json" to the request
    And I execute API request
    Then I check success statusCode of "400 bad request"

  Scenario: DELETE Request Created Successful
    When I create "DELETE" request for the@ endpoint "POSTexampleApiEndpoint"
    And I execute API request
    Then I check success statusCode of "201 created"