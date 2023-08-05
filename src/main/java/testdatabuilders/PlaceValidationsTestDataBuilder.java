package testdatabuilders;

import java.util.List;

import requestbeans.AddPlaceRequest;
import requestbeans.Location;

public class PlaceValidationsTestDataBuilder {

	public AddPlaceRequest getAddPlaceRequest(String name, String address) {
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		AddPlaceRequest request = new AddPlaceRequest();
		request.setLocation(location);
		request.setAccuracy(50);
		request.setName(name);
		request.setPhone_number("(+91) 983 893 3937");
		request.setAddress(address);
		request.setTypes(List.of("shoe park", "shop"));
		request.setWebsite("http://google.com");
		request.setLanguage("French-IN");
		return request;
	}

}
