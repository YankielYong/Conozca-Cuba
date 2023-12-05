package dto;


public class ContractTransportDTO {

	private int contractCodde;
	private int transportCode;

	public ContractTransportDTO(int contractCodde, int transportCode) {
		this.contractCodde = contractCodde;
		this.transportCode = transportCode;
	}

	public int getContractCodde() {
		return contractCodde;
	}

	public void setContractCodde(int contractCodde) {
		this.contractCodde = contractCodde;
	}

	public int getTransportCode() {
		return transportCode;
	}

	public void setTransportCode(int transportCode) {
		this.transportCode = transportCode;
	}
}
