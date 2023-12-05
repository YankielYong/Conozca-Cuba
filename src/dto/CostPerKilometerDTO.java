package dto;

public class CostPerKilometerDTO extends TransportModalityDTO{

	private double costPerKm;
	private double costPerKmRoundTrip;
	private double costPerWaitingHours;
	
	public CostPerKilometerDTO(int modalityCode, String modalityType, double costPerKm, 
			double costPerKmRoundTrip, double costPerWaitingHours) {
		super(modalityCode, modalityType);
		this.costPerKm = costPerKm;
		this.costPerKmRoundTrip = costPerKmRoundTrip;
		this.costPerWaitingHours = costPerWaitingHours;
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
