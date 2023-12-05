package dto;

public class CostPerHourKilometerDTO extends TransportModalityDTO{

	private double costPerKmTraveled;
	private double costPerHour;
	private double costForExtraKm;
	private double costForExtraHours;
	
	public CostPerHourKilometerDTO(int modalityCode, String modalityType, double costPerKmTraveled,
			double costPerHour, double costForExtraKm, double costForExtraHours) {
		super(modalityCode, modalityType);
		this.costPerKmTraveled = costPerKmTraveled;
		this.costPerHour = costPerHour;
		this.costForExtraKm = costForExtraKm;
		this.costForExtraHours = costForExtraHours;
	}

	public double getCostPerKmTraveled() {
		return costPerKmTraveled;
	}

	public void setCostPerKmTraveled(double costPerKmTraveled) {
		this.costPerKmTraveled = costPerKmTraveled;
	}

	public double getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
	}

	public double getCostForExtraKm() {
		return costForExtraKm;
	}

	public void setCostForExtraKm(double costForExtraKm) {
		this.costForExtraKm = costForExtraKm;
	}

	public double getCostForExtraHours() {
		return costForExtraHours;
	}

	public void setCostForExtraHours(double costForExtraHours) {
		this.costForExtraHours = costForExtraHours;
	}
}
