package utils;

public enum AppConstants {

	AddPlaceApi("/maps/api/place/add/json"),
	DeletePlaceApi("/maps/api/place/delete/json"),
	AppPropPath("src//test//resources//properties//application.properties");

	private String label;

	AppConstants(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
