package com.trch.dto;

public class BandAlbumDTO {
	
	private int bandId;
	private String bandName;
	private int albumId;
	private String albumName;
	
	public BandAlbumDTO(int bandId, String bandName, int albumId, String albumName) {
		this.bandId = bandId;
		this.bandName = bandName;
		this.albumId = albumId;
		this.albumName = albumName;
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

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	@Override
	public String toString() {
		return "BandAlbumDTO [bandId=" + bandId + ", bandName=" + bandName + ", albumId=" + albumId + ", albumName="
				+ albumName + "]";
	}
	
	

}
