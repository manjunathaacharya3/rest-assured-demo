package requestbeans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddPlaceRequest {
	
	private Location location;
	private int accuracy;
	private String name;
	private String phone_number;
	private String address;
	private List<String> types;
	private String website;
	private String language;

}
