package com.trch.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.trch.model.Band;
import com.trch.model.Gig;
import com.trch.repository.BandRepository;
import com.trch.repository.GigRepository;
import com.trch.utility.JsonConverter;

public class GigService {
	
	private GigRepository gigRepository;
	
	private JsonConverter<Gig> jsonConverter;
	
	public GigService() {
		
		gigRepository = new GigRepository();
		jsonConverter = new JsonConverter<>();
	}

	public String getJsonStringForAll() {
		
		return jsonConverter.convertToJson(gigRepository.findAllGigs(), "gigs");
	}
	
	public String getJsonStringForOne (String id) {
		
		int gigId = 0;
		
		try {
			gigId = Integer.valueOf(id);
			
		} catch (NumberFormatException e) {
			Logger logger = Logger.getLogger(AlbumService.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		}
		
		return jsonConverter.convertToJson(gigRepository.findGigByGigId(gigId), "gigs");

}
	
	public Gig convertFromJson (String json) {
		
    	Gig gig =  new Gson().fromJson(json, Gig.class);
    	
    	return gig;
    }
	
}
