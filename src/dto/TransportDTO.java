package dto;

public class TransportDTO {

	private int transportCode;
	private int vehicleCode;
	private int modalityCode;
	private String transportBorrower;
	
	public TransportDTO(int transportCode, int vehicleCode, int modalityCode, String transportBorrower) {
		this.transportCode = transportCode;
		this.vehicleCode = vehicleCode;
		this.modalityCode = modalityCode;
		this.transportBorrower = transportBorrower;
	}

	public int getTransportCode() {
		return transportCode;
	}

	public void setTransportCode(int transportCode) {
		this.transportCode = transportCode;
	}

	public int getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(int vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public int getModalityCode() {
		return modalityCode;
	}

	public void setModalityCode(int modalityCode) {
		this.modalityCode = modalityCode;
	}

	public String getTransportBorrower() {
		return transportBorrower;
	}

	public void setTransportBorrower(String transportBorrower) {
		this.transportBorrower = transportBorrower;
	}
}
