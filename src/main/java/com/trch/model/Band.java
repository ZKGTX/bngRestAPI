package com.trch.model;

import java.util.ArrayList;
import java.util.List;

public class Band {
	
	private int id;
	
	private String name;
	
	private List<Album> albums;
	
	private List<Gig> gigs;

//	public Band(int id, String name, List<Album> albums, List<Gig> gigs) {
//		this.id = id;
//		this.name = name;
//		this.albums = albums;
//		this.gigs = gigs;
//	}

	public Band() {
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

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Gig> getGigs() {
		return gigs;
	}

	public void setGigs(List<Gig> gigs) {
		this.gigs = gigs;
	}
	
	
	
	
	
	
	
}

