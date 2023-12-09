package dto;

public class PlaceDTO {

	private int placeCode;
	private String placeName;
	private double costPerPerson;
	private String typeOfService;

	public PlaceDTO(int placeCode, String placeName, double costPerPerson, String typeOfService) {
		super();
		this.placeCode = placeCode;
		this.placeName = placeName;
		this.costPerPerson = costPerPerson;
		this.typeOfService = typeOfService;
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

	public double getCostPerPerson() {
		return costPerPerson;
	}

	public void setCostPerPerson(double costPerPerson) {
		this.costPerPerson = costPerPerson;
	}

	public String getTypeOfService() {
		return typeOfService;
	}

	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}
}
