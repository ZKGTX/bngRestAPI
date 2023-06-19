package com.trch.dto;

public class BandGigDTO {
	
	private int bandId;
	private String bandName;
	private int gigId;
	private String gigName;
	
	public BandGigDTO(int bandId, String bandName, int gigId, String gigName) {
		super();
		this.bandId = bandId;
		this.bandName = bandName;
		this.gigId = gigId;
		this.gigName = gigName;
	}

	public int getBandId() {
		return bandId;
	}

	public void setBandId(int bandId) {
		this.bandId = bandId;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public int getGigId() {
		return gigId;
	}

	public void setGigId(int gigId) {
		this.gigId = gigId;
	}

	public String getGigName() {
		return gigName;
	}

	public void setGigName(String gigName) {
		this.gigName = gigName;
	}

	@Override
	public String toString() {
		return "BandGigDTO [bandId=" + bandId + ", bandName=" + bandName + ", gigId=" + gigId + ", gigName=" + gigName
				+ "]";
	}
	
	
	
	

}
