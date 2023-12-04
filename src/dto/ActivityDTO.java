package dto;

import java.util.Date;

public class ActivityDTO {

	private int activityCode;
	private Date activityDate;
	private double activityPrice;
	private String activityDescription;
	
	public ActivityDTO(int activityCode, Date activityDate,	double activityPrice, String activityDescription) {
		this.activityCode = activityCode;
		this.activityDate = activityDate;
		this.activityPrice = activityPrice;
		this.activityDescription = activityDescription;
	}

	public int getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(int activityCode) {
		this.activityCode = activityCode;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public double getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(double activityPrice) {
		this.activityPrice = activityPrice;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
}
