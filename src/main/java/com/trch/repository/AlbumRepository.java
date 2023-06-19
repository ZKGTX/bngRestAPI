package com.trch.repository;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trch.config.DbConfig;
import com.trch.config.DbInitializer;
import com.trch.dto.BandAlbumDTO;
import com.trch.model.Album;
import com.trch.model.Band;
import com.trch.model.Gig;
import com.trch.utility.JsonConverter;



public class AlbumRepository {
	
	public Album findAlbumByAlbumId (int albumId) {
		
		Album album = new Album();
		
		try (Connection connection = DbConfig.getConnection()) 

		{
			
			
			PreparedStatement pst = connection.prepareStatement
					("SELECT a.id, a.name, b.id, b.name FROM albums a INNER JOIN bands b ON a.band_id = b.id WHERE a.id = ?;");
			
			pst.setInt(1, albumId);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				album.setId(rs.getInt(1));
				album.setName(rs.getString(2));
				Band band = new Band();
				band.setId(rs.getInt(3));
				band.setName(rs.getString(4));
				album.setBand(band);
			}
			
			rs.close();
			pst.close();
			connection.close();
			
		} catch (SQLException e) {
			Logger logger = Logger.getLogger(AlbumRepository.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		} 
		
		return album;
	}
	
	
	public List<Album> findAllAlbums() {
		
		List<Album> albums = new ArrayList<>();
				
		try {
			
			Connection connection = DbConfig.getConnection();
			
			PreparedStatement pst = connection.prepareStatement("SELECT a.id, a.name, b.id, b.name FROM albums a INNER JOIN bands b ON a.band_id = b.id;");
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Album album = new Album();
				album.setId(rs.getInt(1));
				album.setName(rs.getString(2));
				Band band = new Band();
				band.setId(rs.getInt(3));
				band.setName(rs.getString(4));
				album.setBand(band);
				albums.add(album);
			}
			
			rs.close();
			pst.close();
			connection.close();
			
		} catch (SQLException e) {
			Logger logger = Logger.getLogger(AlbumRepository.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		} 
		
		return albums;
}

	
	public List<Album> findAlbumsByBandId(ResultSet rs) throws SQLException {
		
		List<Album> albumsByBand = new ArrayList<>();
		
		while (rs.next()) {
			Album album = new Album();
			
				album.setId(rs.getInt(3));
				album.setName(rs.getString(4));
				albumsByBand.add(album);
			
			} 
	
		return albumsByBand;
}
	
	public List<Album> findAlbumsByBandId(List<BandAlbumDTO> objectList, int bandId) throws SQLException {
		
		List<Album> albumsByBand = new ArrayList<>();
		
		for (BandAlbumDTO dto: objectList) {
			System.out.println(dto.getBandId() == bandId);
			Album album = new Album();
			if (dto.getBandId() == bandId) {
				album.setId(dto.getAlbumId());
				album.setName(dto.getAlbumName());
				albumsByBand.add(album);
			}
			} 
	
		return albumsByBand;
}
}

	
