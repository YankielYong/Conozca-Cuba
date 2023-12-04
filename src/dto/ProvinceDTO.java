package dto;

public class ProvinceDTO {

	private int provinceCode;
	private String proviceName;
	
	public ProvinceDTO(int provinceCode, String proviceName) {
		this.provinceCode = provinceCode;
		this.proviceName = proviceName;
	}

	public int getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(int provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProviceName() {
		return proviceName;
	}

	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}
}
