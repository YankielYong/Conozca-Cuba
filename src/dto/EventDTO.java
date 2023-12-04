package dto;

public class EventDTO {

	private int eventCode;
	private int placeCode;
	private int activityCode;
	
	public EventDTO(int eventCode, int placeCode, int activityCode) {
		this.eventCode = eventCode;
		this.placeCode = placeCode;
		this.activityCode = activityCode;
	}

	public int getEventCode() {
		return eventCode;
	}

	public void setEventCode(int eventCode) {
		this.eventCode = eventCode;
	}

	public int getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(int placeCode) {
		this.placeCode = placeCode;
	}

	public int getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(int activityCode) {
		this.activityCode = activityCode;
	}
}
