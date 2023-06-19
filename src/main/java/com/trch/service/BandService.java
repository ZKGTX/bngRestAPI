package com.trch.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.trch.model.Band;
import com.trch.repository.BandRepository;
import com.trch.utility.JsonConverter;

public class BandService {
	
	private BandRepository bandRepository;
	
	private JsonConverter<Band> jsonConverter;
	
	public BandService() {
		
		bandRepository = new BandRepository();
		jsonConverter = new JsonConverter<>();
	}

	public String getJsonStringForAll() {
		
		return jsonConverter.convertToJson(bandRepository.findAllBands(), "bands");
	}
	
	public String getJsonStringForOne (String id) {
		
		int bandId = 0;
		
		try {
			bandId = Integer.valueOf(id);
			
		} catch (NumberFormatException e) {
			Logger logger = Logger.getLogger(AlbumService.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		}
		
		return jsonConverter.convertToJson(bandRepository.findBandByBandId(bandId), "bands");

}
	
	public Band convertFromJson (String json) {
		
    	Band band =  new Gson().fromJson(json, Band.class);
    	
    	return band;
    }
	
}