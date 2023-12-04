package dto;

public class HotelDTO {

	private int hotelCode;
	private String hotelName;
	private String hotelAddress;
	private int hotelCategory;
	private String hotelPhone;
	private String hotelFax;
	private String hotelMail;
	private String hotelLocation;
	private String hotelModality;
	private int numberOfRooms;
	private int numberOfFloors;
	private double nearbyCityDistance;
	private double airportDistance;
	private int hotelChainCode;
	private int provinceCode;
	
	public HotelDTO(int hotelCode, String hotelName, String hotelAddress, int hotelCategory, String hotelPhone,
			String hotelFax, String hotelMail, String hotelLocation, String hotelModality, int numberOfRooms, 
			int numberOfFloors, double nearbyCityDistance, double airportDistance, int hotelChainCode, int provinceCode) {
		this.hotelCode = hotelCode;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.hotelCategory = hotelCategory;
		this.hotelPhone = hotelPhone;
		this.hotelFax = hotelFax;
		this.hotelMail = hotelMail;
		this.hotelLocation = hotelLocation;
		this.hotelModality = hotelModality;
		this.numberOfRooms = numberOfRooms;
		this.numberOfFloors = numberOfFloors;
		this.nearbyCityDistance = nearbyCityDistance;
		this.airportDistance = airportDistance;
		this.hotelChainCode = hotelChainCode;
		this.provinceCode = provinceCode;
	}

	public int getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(int hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public int getHotelCategory() {
		return hotelCategory;
	}

	public void setHotelCategory(int hotelCategory) {
		this.hotelCategory = hotelCategory;
	}

	public String getHotelPhone() {
		return hotelPhone;
	}

	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}

	public String getHotelFax() {
		return hotelFax;
	}

	public void setHotelFax(String hotelFax) {
		this.hotelFax = hotelFax;
	}

	public String getHotelMail() {
		return hotelMail;
	}

	public void setHotelMail(String hotelMail) {
		this.hotelMail = hotelMail;
	}

	public String getHotelLocation() {
		return hotelLocation;
	}

	public void setHotelLocation(String hotelLocation) {
		this.hotelLocation = hotelLocation;
	}

	public String getHotelModality() {
		return hotelModality;
	}

	public void setHotelModality(String hotelModality) {
		this.hotelModality = hotelModality;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public double getNearbyCityDistance() {
		return nearbyCityDistance;
	}

	public void setNearbyCityDistance(double nearbyCityDistance) {
		this.nearbyCityDistance = nearbyCityDistance;
	}

	public double getAirportDistance() {
		return airportDistance;
	}

	public void setAirportDistance(double airportDistance) {
		this.airportDistance = airportDistance;
	}

	public int getHotelChainCode() {
		return hotelChainCode;
	}

	public void setHotelChainCode(int hotelChainCode) {
		this.hotelChainCode = hotelChainCode;
	}

	public int getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(int provinceCode) {
		this.provinceCode = provinceCode;
	}
}
