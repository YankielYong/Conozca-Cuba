package dto;

public class ProvincePlaceDTO {

	private int provincePlaceCode;
	private int provinceCode;
	private int placeCode;
	
	public ProvincePlaceDTO(int provincePlaceCode, int provinceCode, int placeCode) {
		this.provincePlaceCode = provincePlaceCode;
		this.provinceCode = provinceCode;
		this.placeCode = placeCode;
	}

	public int getProvincePlaceCode() {
		return provincePlaceCode;
	}

	public void setProvincePlaceCode(int provincePlaceCode) {
		this.provincePlaceCode = provincePlaceCode;
	}

	public int getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(int provinceCode) {
		this.provinceCode = provinceCode;
	}

	public int getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(int placeCode) {
		this.placeCode = placeCode;
	}
}
