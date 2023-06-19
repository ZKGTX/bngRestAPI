package com.trch.model;

import java.util.ArrayList;
import java.util.List;

public class Gig {
	
	private int id;
	
	private String name;
	
	private List<Band> bands;

	public Gig(int id, String name, List<Band> bands) {
		super();
		this.id = id;
		this.name = name;
		this.bands = bands;
	}

	public Gig() {
		
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

	public List<Band> getBands() {
		return bands;
	}

	public void setBands(List<Band> bands) {
		this.bands = bands;
	}
	
	
	
	
	
	

}