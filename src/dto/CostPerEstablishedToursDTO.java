package dto;

public class CostPerEstablishedToursDTO extends TransportModalityDTO{

	private String tourDescription;
	private double costPerTour;
	private double costPerTourRoundTrip;
	
	public CostPerEstablishedToursDTO(int modalityCode, String modalityType, String tourDescription, 
			double costPerTour, double costPerTourRoundTrip) {
		super(modalityCode, modalityType);
		this.tourDescription = tourDescription;
		this.costPerTour = costPerTour;
		this.costPerTourRoundTrip = costPerTourRoundTrip;
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
