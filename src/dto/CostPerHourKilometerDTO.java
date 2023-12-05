package dto;

public class CostPerHourKilometerDTO {

	private int modalityCode;
	private double costPerKmTraveled;
	private double costPerHour;
	private double costForExtraKm;
	private double costForExtraHours;

	public CostPerHourKilometerDTO(int modalityCode, double costPerKmTraveled,
			double costPerHour, double costForExtraKm, double costForExtraHours) {
		this.modalityCode = modalityCode;
		this.costPerKmTraveled = costPerKmTraveled;
		this.costPerHour = costPerHour;
		this.costForExtraKm = costForExtraKm;
		this.costForExtraHours = costForExtraHours;
	}

	public int getModalityCode() {
		return modalityCode;
	}

	public void setModalityCode(int modalityCode) {
		this.modalityCode = modalityCode;
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
