package dto;

import java.util.Date;

public class ContractLodgingDTO extends ContractDTO{

	private int lodgingCode;

	public ContractLodgingDTO(int contractCode, String contractDescription, Date contractStartDate,Date contractEndDate,
			Date contractConciliationDate, String contractType, int packageCode, int lodgingCode) {
		super(contractCode, contractDescription, contractStartDate, contractEndDate,
				contractConciliationDate, contractType, packageCode);
		this.lodgingCode = lodgingCode;
	}

	public int getLodgingCode() {
		return lodgingCode;
	}

	public void setLodgingCode(int lodgingCode) {
		this.lodgingCode = lodgingCode;
	}
}
