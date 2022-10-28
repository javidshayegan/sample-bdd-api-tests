package bddtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ApiAutomationStepsDef extends ApiHandler {

    Response response;

    @Given("I set up the baseURI for the API")
    public String i_set_up_the_base_uri() throws IOException {
        getBaseUriValue();
        return baseUrl;
    }

    @Given("I create {string} request for the@ endpoint {string}")
    public void i_set_get_request_for_the_entries_endpoint(String endPoint, String requestType) throws IOException {
        //reqObj = given().spec(requestSpecificationMethod(baseUrl + endPoint));
        //Other way
        ApiHandler.createAPIRequest(endPoint, requestType);

    }

    @When("I execute API request")
    public void i_execute_the_api_request() {
        // int response = reqObj.get().statusCode();
        ApiHandler.executeRequest();
    }

    @Then("I check success statusCode of {string}")
    public void i_check_if_the_api_call_is_returning_a_success_status_code_of(String expectedResponse) {
        //Assert.assertEquals(response.getStatusCode(), statusCode);
        //Other Way
        ApiHandler.validateResponseCode(expectedResponse);
    }

    @Then("I save the endpoint response to JsonACTUAL file")
    public void i_save_the_endpoint_response_to_json_actual_file() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I Compare the expected response json JsonEXPECTED and JsonACTUAL")
    public void i_compare_the_expected_response_json_json_expected_and_json_actual() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }

    @When("I had the below Query Parameters to the request")
    public void i_had_the_below_quary_paramters_to_the_request(io.cucumber.datatable.DataTable dataTable) {
        //List> list = dataTable.asMaps(String.class, String.class);
        //ApiHandler.AddQueryParameters(/**list*/);

    }

    @When("I add the json body {string} to the request")
    public void i_add_the_json_body_example_json_to_the_request(String fileName) {

        String fullName = "src/test/resources/json-files" + fileName;
        //String filePath = fullName.GetFilePath();
        //String reqBody = File.ReadAllText(filePath);
        // ApiHandler.AddQueryParamatersBody("application/json", reqBody, PramaterType.RequestBody);

    }

}