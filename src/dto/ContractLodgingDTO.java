package dto;

public class ContractLodgingDTO {

	private int contractCode;
	private int lodgingCode;

	public ContractLodgingDTO(int contractCode, int lodgingCode) {
		this.contractCode = contractCode;
		this.lodgingCode = lodgingCode;
	}

	public int getContractCode() {
		return contractCode;
	}

	public void setContractCode(int contractCode) {
		this.contractCode = contractCode;
	}

	public int getLodgingCode() {
		return lodgingCode;
	}

	public void setLodgingCode(int lodgingCode) {
		this.lodgingCode = lodgingCode;
	}
}
