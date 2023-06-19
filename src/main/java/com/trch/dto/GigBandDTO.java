package com.trch.dto;

public class GigBandDTO {
	
	private int gigId;
	private String gigName;
	private int bandId;
	private String bandName;
	
	public GigBandDTO(int gigId, String gigName, int bandId, String bandName) {
		super();
		this.gigId = gigId;
		this.gigName = gigName;
		this.bandId = bandId;
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
	
	
	
	

}
