package dto;

public class TransportModalityDTO {

	private int modalityCode;
	private String modalityType;
	
	public TransportModalityDTO(int modalityCode, String modalityType) {
		this.modalityCode = modalityCode;
		this.modalityType = modalityType;
	}

	public int getModalityCode() {
		return modalityCode;
	}

	public void setModalityCode(int modalityCode) {
		this.modalityCode = modalityCode;
	}

	public String getModalityType() {
		return modalityType;
	}

	public void setModalityType(String modalityType) {
		this.modalityType = modalityType;
	}
}
