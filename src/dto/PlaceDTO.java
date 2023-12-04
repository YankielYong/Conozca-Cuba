package dto;

public class PlaceDTO {

	private int placeCode;
	private String placeName;
	
	public PlaceDTO(int placeCode, String placeName) {
		this.placeCode = placeCode;
		this.placeName = placeName;
	}

	public int getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(int placeCode) {
		this.placeCode = placeCode;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
}
