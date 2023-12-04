package dto;

public class RoomDTO {

	private int roomCode;
	private String roomType;
	private double surchargeRoom;
	private int foodPlanCode;
	
	public RoomDTO(int roomCode, String roomType, double surchargeRoom, int foodPlanCode) {
		this.roomCode = roomCode;
		this.roomType = roomType;
		this.surchargeRoom = surchargeRoom;
		this.foodPlanCode = foodPlanCode;
	}

	public int getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(int roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getSurchargeRoom() {
		return surchargeRoom;
	}

	public void setSurchargeRoom(double surchargeRoom) {
		this.surchargeRoom = surchargeRoom;
	}

	public int getFoodPlanCode() {
		return foodPlanCode;
	}

	public void setFoodPlanCode(int foodPlanCode) {
		this.foodPlanCode = foodPlanCode;
	}
}
