package dto;

public class CostPerEstablishedToursDTO {

	private int modalityCode;
	private String tourDescription;
	private double costPerTour;
	private double costPerTourRoundTrip;

	public CostPerEstablishedToursDTO(int modalityCode, String tourDescription,
			double costPerTour, double costPerTourRoundTrip) {
		this.modalityCode = modalityCode;
		this.tourDescription = tourDescription;
		this.costPerTour = costPerTour;
		this.costPerTourRoundTrip = costPerTourRoundTrip;
	}

	public int getModalityCode() {
		return modalityCode;
	}

	public void setModalityCode(int modalityCode) {
		this.modalityCode = modalityCode;
	}

	public String getTourDescription() {
		return tourDescription;
	}

	public void setTourDescription(String tourDescription) {
		this.tourDescription = tourDescription;
	}

	public double getCostPerTour() {
		return costPerTour;
	}

	public void setCostPerTour(double costPerTour) {
		this.costPerTour = costPerTour;
	}

	public double getCostPerTourRoundTrip() {
		return costPerTourRoundTrip;
	}

	public void setCostPerTourRoundTrip(double costPerTourRoundTrip) {
		this.costPerTourRoundTrip = costPerTourRoundTrip;
	}	
}
