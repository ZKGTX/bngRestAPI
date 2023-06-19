package com.trch.dto;

public class GigDTO {
	
	private int gigId;
	private String gigName;
	
	public GigDTO(int gigId, String gigName) {
		this.gigId = gigId;
		this.gigName = gigName;
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
		return "GigDTO [gigId=" + gigId + ", gigName=" + gigName + "]";
	}
	
	
	
	

}
