package com.trch.model;


public class Album {
	
	private int id;
	
	private String name;
	
	private Band band;

	public Album(int id, String name, Band band) {
		this.id = id;
		this.name = name;
		this.band = band;
	}

	public Album() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}
	
	
	
	
	
	
	
	


}