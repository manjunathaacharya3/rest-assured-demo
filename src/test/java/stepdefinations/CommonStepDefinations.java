package stepdefinations;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.AppConstants;

public class CommonStepDefinations extends BaseService {

	@When("User calls {string} with {string} request")
	public void user_calls_with_request(String endPointName, String callType) {
		String endPoint = AppConstants.valueOf(endPointName).getLabel();
		response = sendRequestToService(callType, endPoint);
	}

	@Then("Verify the response code is {int}")
	public void verify_the_response_code_is(Integer int1) {
		assertEquals(response.getStatusCode(), int1.intValue());
	}

	@Then("Verify the {string} in the response body is {string}")
	public void verify_the_in_the_response_body_is(String jsonPath, String value) {
		assertEquals(value, getValueFromJson(response.then().extract().response().asString(), jsonPath).toString());
	}

}
