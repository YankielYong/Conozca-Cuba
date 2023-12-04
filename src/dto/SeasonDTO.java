package dto;

import java.util.Date;

public class SeasonDTO {

	private int seasonCode;
	private String seasonName;
	private String seasonDescription;
	private Date seasonStartDate;
	private Date seasonEndDate;
	
	public SeasonDTO(int seasonCode, String seasonName, String seasonDescription,
			Date seasonStartDate, Date seasonEndDate) {
		this.seasonCode = seasonCode;
		this.seasonName = seasonName;
		this.seasonDescription = seasonDescription;
		this.seasonStartDate = seasonStartDate;
		this.seasonEndDate = seasonEndDate;
	}

	public int getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(int seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public String getSeasonDescription() {
		return seasonDescription;
	}

	public void setSeasonDescription(String seasonDescription) {
		this.seasonDescription = seasonDescription;
	}

	public Date getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(Date seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public Date getSeasonEndDate() {
		return seasonEndDate;
	}

	public void setSeasonEndDate(Date seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}
}
