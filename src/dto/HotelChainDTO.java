package dto;

public class HotelChainDTO {

	private int hotelChainCode;
	private String hotelChainName;
	
	public HotelChainDTO(int hotelChainCode, String hotelChainName) {
		this.hotelChainCode = hotelChainCode;
		this.hotelChainName = hotelChainName;
	}

	public int getHotelChainCode() {
		return hotelChainCode;
	}

	public void setHotelChainCode(int hotelChainCode) {
		this.hotelChainCode = hotelChainCode;
	}

	public String getHotelChainName() {
		return hotelChainName;
	}

	public void setHotelChainName(String hotelChainName) {
		this.hotelChainName = hotelChainName;
	}
}
