package dto;

import java.util.Date;

public class ContractTransportDTO extends ContractDTO{

	private int transportCode;

	public ContractTransportDTO(int contractCode, String contractDescription, Date contractStartDate,
			Date contractEndDate, Date contractConciliationDate, String contractType, int packageCode, int transportCode) {
		super(contractCode, contractDescription, contractStartDate, contractEndDate,
				contractConciliationDate, contractType, packageCode);
		this.transportCode = transportCode;
	}

	public int getTransportCode() {
		return transportCode;
	}

	public void setTransportCode(int transportCode) {
		this.transportCode = transportCode;
	}
}
