package dto;

public class LodgingDTO {

	private int lodgingCode;
	private int hotelCode;
	private int seasonCode;
	private int roomCode;
	private double lodgingPrice;
	
	public LodgingDTO(int lodgingCode, int hotelCode, int seasonCode, int roomCode, double lodgingPrice) {
		this.lodgingCode = lodgingCode;
		this.hotelCode = hotelCode;
		this.seasonCode = seasonCode;
		this.roomCode = roomCode;
		this.lodgingPrice = lodgingPrice;
	}

	public int getLodgingCode() {
		return lodgingCode;
	}

	public void setLodgingCode(int lodgingCode) {
		this.lodgingCode = lodgingCode;
	}

	public int getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(int hotelCode) {
		this.hotelCode = hotelCode;
	}

	public int getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(int seasonCode) {
		this.seasonCode = seasonCode;
	}

	public int getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(int roomCode) {
		this.roomCode = roomCode;
	}

	public double getLodgingPrice() {
		return lodgingPrice;
	}

	public void setLodgingPrice(double lodgingPrice) {
		this.lodgingPrice = lodgingPrice;
	}
}
