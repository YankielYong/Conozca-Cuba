package dto;

public class TouristPackageDTO {
	
	private int packageCode;
	private String promotionalName;
	private double packagePrice;
	private double packageCost;
	private int numberOfPeople;
	private int numberOfDays;
	private int numberOfNights;
	
	public TouristPackageDTO(int packageCode, String promotionalName, double packagePrice,
			double packageCost, int numberOfPeople, int numberOfDays, int numberOfNights) {
		this.packageCode = packageCode;
		this.promotionalName = promotionalName;
		this.packagePrice = packagePrice;
		this.packageCost = packageCost;
		this.numberOfPeople = numberOfPeople;
		this.numberOfDays = numberOfDays;
		this.numberOfNights = numberOfNights;
	}

	public int getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(int packageCode) {
		this.packageCode = packageCode;
	}

	public String getPromotionalName() {
		return promotionalName;
	}

	public void setPromotionalName(String promotionalName) {
		this.promotionalName = promotionalName;
	}

	public double getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(double packagePrice) {
		this.packagePrice = packagePrice;
	}

	public double getPackageCost() {
		return packageCost;
	}

	public void setPackageCost(double packageCost) {
		this.packageCost = packageCost;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public int getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(int numberOfNights) {
		this.numberOfNights = numberOfNights;
	}
}
