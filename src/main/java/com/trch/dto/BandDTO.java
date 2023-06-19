package com.trch.dto;

public class BandDTO {
	
	private int bandId;
	private String bandName;
	
	public BandDTO(int bandId, String bandName) {
		this.bandId = bandId;
		this.bandName = bandName;
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

	@Override
	public String toString() {
		return "BandDTO [bandId=" + bandId + ", bandName=" + bandName + "]";
	}
	
	
	
	

}
