package dto;

import java.util.Date;

public class ContractEventDTO extends ContractDTO{
	
	private int eventCode;

	public ContractEventDTO(int contractCode, String contractDescription, Date contractStartDate, 
			Date contractEndDate, Date contractConciliationDate, String contractType, int packageCode, int eventCode) {
		super(contractCode, contractDescription, contractStartDate, contractEndDate, 
				contractConciliationDate,  contractType, packageCode);
		this.eventCode = eventCode;
	}

	public int getEventCode() {
		return eventCode;
	}

	public void setEventCode(int eventCode) {
		this.eventCode = eventCode;
	}
}
