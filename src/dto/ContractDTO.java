package dto;

import java.util.Date;

public abstract class ContractDTO {

	protected int contractCode;
	protected String contractDescription;
	protected Date contractStartDate;
	protected Date contractEndDate;
	protected Date contractConciliationDate;
	protected String contractType;
	protected int packageCode;
	
	public ContractDTO(int contractCode, String contractDescription, Date contractStartDate, 
			Date contractEndDate, Date contractConciliationDate, String contractType, int packageCode) {
		this.contractCode = contractCode;
		this.contractDescription = contractDescription;
		this.contractStartDate = contractStartDate;
		this.contractEndDate = contractEndDate;
		this.contractConciliationDate = contractConciliationDate;
		this.contractType = contractType;
		this.packageCode = packageCode;
	}

	public int getContractCode() {
		return contractCode;
	}

	public void setContractCode(int contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractDescription() {
		return contractDescription;
	}

	public void setContractDescription(String contractDescription) {
		this.contractDescription = contractDescription;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Date getContractConciliationDate() {
		return contractConciliationDate;
	}

	public void setContractConciliationDate(Date contractConciliationDate) {
		this.contractConciliationDate = contractConciliationDate;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public int getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(int packageCode) {
		this.packageCode = packageCode;
	}
}
