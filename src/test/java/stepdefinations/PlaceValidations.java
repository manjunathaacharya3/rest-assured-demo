package stepdefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testdatabuilders.PlaceValidationsTestDataBuilder;
import utils.PropertyReader;
import utils.ExcelReader;
import utils.ObjectFactory;

public class PlaceValidations extends BaseService {

	private PlaceValidationsTestDataBuilder placeValidationsBuilder = ObjectFactory
			.getInstance(PlaceValidationsTestDataBuilder.class);

	@Given("Prepare the add new place api request")
	public void prepare_the_add_new_place_api_request(DataTable dataTable) {
		loadBaseRequestSpec();
		String name = PropertyReader.loadDataTableMap(dataTable).get("name");
		String address = PropertyReader.loadDataTableMap(dataTable).get("address");
		super.requestBody = placeValidationsBuilder.getAddPlaceRequest(name, address);
	}

	@Given("Prepare the delete place api request")
	public void prepare_the_delete_place_api_request() {
		super.requestBody = Map.of("place_id",
				getValueFromJson(response.then().extract().response().asString(), "place_id"));
	}
}
