package stepdefinations;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import utils.AppConstants;
import utils.ConfigReport;
import utils.Loader;

@Setter
@Getter
public class BaseService {

	protected static RequestSpecification baseRequestSpecification;
	protected static RequestSpecification requestSpecification;
	protected static Response response;

	protected static Object requestBody;

	protected void loadBaseRequestSpec() {
		if (baseRequestSpecification == null) {
			try {
				PrintStream psLogs = new PrintStream(new File("logs.txt"));
				baseRequestSpecification = new RequestSpecBuilder()
						.setBaseUri(Loader.loadPropertyFile(AppConstants.AppPropPath.getLabel()).getProperty("baseUrl"))
						.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
						.addFilter(RequestLoggingFilter.logRequestTo(psLogs))
						.addFilter(ResponseLoggingFilter.logResponseTo(psLogs)).build();
			} catch (Exception e) {
				throw new RuntimeException("Exception while creating the request builder..." + e.getMessage());
			}
		}
	}

	protected Response sendRequestToService(String callType, String endPoint) {
		if (requestBody == null || callType == null || endPoint == null) {
			throw new RuntimeException(
					String.format("None of the values {requestBody - %s, callType - %s, endPoint - %s}  should be null",
							requestBody, callType, endPoint));
		}
		Response response;
		RequestSpecification requestSpecification = given().spec(baseRequestSpecification).body(requestBody);
		ConfigReport.logInfoToReport("URL: ", endPoint);
		ConfigReport.logJsonContentToReport("Request: ", requestBody);
		switch (callType.toUpperCase()) {
		case "GET":
			response = requestSpecification.when().get(endPoint);
			break;
		case "POST":
			response = requestSpecification.when().post(endPoint);
			break;
		case "PUT":
			response = requestSpecification.when().put(endPoint);
			break;
		case "DELETE":
			response = requestSpecification.when().delete(endPoint);
			break;
		default:
			throw new RuntimeException("No suitable HTTP calls found for - " + callType);
		}
		ConfigReport.logJsonContentToReport("Response: ", response.then().extract().asString());
		resetTheRequestBodyHolder();
		return response;
	}

	private void resetTheRequestBodyHolder() {
		if (requestBody != null) {
			requestBody = null;
		}
	}

	public Object getValueFromJson(String response, String jsonPath) {
		if (response.isBlank() || response == null) {
			throw new RuntimeException("Invalid response passed to parse the value using json path");
		}
		if (jsonPath.isBlank() || jsonPath == null) {
			throw new RuntimeException("Invalid json path");
		}
		JsonPath js = new JsonPath(response);
		return js.get(jsonPath);
	}
}
