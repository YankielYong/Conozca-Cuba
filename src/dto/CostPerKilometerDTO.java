package dto;

public class CostPerKilometerDTO {

	private int modalityCode;
	private double costPerKm;
	private double costPerKmRoundTrip;
	private double costPerWaitingHours;

	public CostPerKilometerDTO(int modalityCode, double costPerKm,
			double costPerKmRoundTrip, double costPerWaitingHours) {
		this.modalityCode = modalityCode;
		this.costPerKm = costPerKm;
		this.costPerKmRoundTrip = costPerKmRoundTrip;
		this.costPerWaitingHours = costPerWaitingHours;
	}

	public int getModalityCode() {
		return modalityCode;
	}

	public void setModalityCode(int modalityCode) {
		this.modalityCode = modalityCode;
	}

	public double getCostPerKm() {
		return costPerKm;
	}

	public void setCostPerKm(double costPerKm) {
		this.costPerKm = costPerKm;
	}

	public double getCostPerKmRoundTrip() {
		return costPerKmRoundTrip;
	}

	public void setCostPerKmRoundTrip(double costPerKmRoundTrip) {
		this.costPerKmRoundTrip = costPerKmRoundTrip;
	}

	public double getCostPerWaitingHours() {
		return costPerWaitingHours;
	}

	public void setCostPerWaitingHours(double costPerWaitingHours) {
		this.costPerWaitingHours = costPerWaitingHours;
	}
}
