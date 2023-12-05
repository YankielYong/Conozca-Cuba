package dto;

public class ContractEventDTO {
	
	private int contractCode;
	private int eventCode;

	public ContractEventDTO(int contractCode, int eventCode) {
		this.contractCode = contractCode;
		this.eventCode = eventCode;
	}

	public int getContractCode() {
		return contractCode;
	}

	public void setContractCode(int contractCode) {
		this.contractCode = contractCode;
	}

	public int getEventCode() {
		return eventCode;
	}

	public void setEventCode(int eventCode) {
		this.eventCode = eventCode;
	}
}
