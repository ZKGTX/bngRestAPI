package com.trch.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.trch.model.Album;
import com.trch.repository.AlbumRepository;
import com.trch.utility.JsonConverter;

public class AlbumService {
	
	private AlbumRepository albumRepository;
	
	private JsonConverter<Album> jsonConverter;
	
	public AlbumService() {
		
		albumRepository = new AlbumRepository();
		jsonConverter = new JsonConverter<>();
	}

	public String getJsonStringForAll() {
		
		return jsonConverter.convertToJson(albumRepository.findAllAlbums(), "albums");
	}
	
	public String getJsonStringForOne (String id) {
		
		int albumId = 0;
		
		try {
			albumId = Integer.valueOf(id);
			
		} catch (NumberFormatException e) {
			Logger logger = Logger.getLogger(AlbumService.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		}
		
		return jsonConverter.convertToJson(albumRepository.findAlbumByAlbumId(albumId), "albums");

}
	
	public Album convertFromJson (String json) {
		
    	Album album =  new Gson().fromJson(json, Album.class);
    	
    	return album;
    }
	
}
