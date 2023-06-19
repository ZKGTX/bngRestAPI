package com.trch.repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.trch.config.DbConfig;
import com.trch.dto.BandAlbumDTO;
import com.trch.dto.BandDTO;
import com.trch.dto.BandGigDTO;
import com.trch.dto.GigBandDTO;
import com.trch.model.Album;
import com.trch.model.Band;
import com.trch.model.Gig;

public class BandRepository {
	
	private AlbumRepository albumRepository;
	
	private GigRepository gigRepository;
	
	
	public BandRepository() {
		albumRepository = new AlbumRepository();
		gigRepository = new GigRepository();
	}


	public List<Band> findAllBands() {
		
		List<Band> bands = new ArrayList<>();
				
		try (Connection connection = DbConfig.getConnection()) {
			
			PreparedStatement allBandsPSt = connection.prepareStatement
					("SELECT bands.id, bands.name FROM bands;");
			
			PreparedStatement bandsGigsPSt = connection.prepareStatement("SELECT bands.id, bands.name, gigs.id, gigs.name "
					+ "FROM bands JOIN bands_gigs ON bands_gigs.band_id = bands.id"
					+ " JOIN gigs ON bands_gigs.gig_id = gigs.id;");
			
			PreparedStatement albumsPSt = connection.prepareStatement("SELECT bands.id, bands.name, albums.id, albums.name FROM bands JOIN albums ON albums.band_id = bands.id;");
			
			ResultSet bandsRs = allBandsPSt.executeQuery();
			
			ResultSet bandsGigsRs = bandsGigsPSt.executeQuery();
			
			ResultSet albumsRs = albumsPSt.executeQuery();
			
			List<BandDTO> bandsRs_list = new ArrayList<>();
			while (bandsRs.next()) {
				BandDTO dto = new BandDTO(bandsRs.getInt(1), bandsRs.getString(2));
				bandsRs_list.add(dto);
			}
			bandsRs.close();
			allBandsPSt.close();
			
			List<BandAlbumDTO> albumsRs_list = new ArrayList<>();
			while (albumsRs.next()) {
				BandAlbumDTO dto = new BandAlbumDTO(albumsRs.getInt(1), albumsRs.getString(2), albumsRs.getInt(3), albumsRs.getString(4));
				albumsRs_list.add(dto);
			}
			
			albumsRs.close();
			albumsPSt.close();
			
			List<BandGigDTO> bandsGigsRs_list = new ArrayList<>();
			while (bandsGigsRs.next()) {
				BandGigDTO dto = new BandGigDTO(bandsGigsRs.getInt(1), bandsGigsRs.getString(2), bandsGigsRs.getInt(3), bandsGigsRs.getString(4));
				bandsGigsRs_list.add(dto);
			}
			bandsGigsRs.close();
			bandsGigsPSt.close();
			
			for (BandDTO dto : bandsRs_list) {
				System.out.println(dto.toString());
				Band band = new Band();
				band.setId(dto.getBandId());
				band.setName(dto.getBandName());
				List<Album> albums = albumRepository.findAlbumsByBandId(albumsRs_list, band.getId());
				band.setAlbums(albums);
				List<Gig> gigs = new ArrayList();
				gigs.addAll(gigRepository.findGigsByBandId(bandsGigsRs_list, band.getId()));
				band.setGigs(gigs);
				bands.add(band);
			}
			
		} catch (SQLException e) {
			Logger logger = Logger.getLogger(AlbumRepository.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		} 
		
		return bands;
}
	
	public Band findBandByBandId (int bandId) {
		Band band = new Band();
		
		try (Connection connection = DbConfig.getConnection())
		{			
			PreparedStatement pst = connection.prepareStatement
					("SELECT * FROM bands WHERE bands.id = ?;");
			pst.setInt(1, bandId);
			
			ResultSet bandsRs = pst.executeQuery();
			
			PreparedStatement bandsGigsPSt = connection.prepareStatement("SELECT bands.id, bands.name, gigs.id, gigs.name FROM bands JOIN bands_gigs ON bands_gigs.band_id = bands.id JOIN gigs ON bands_gigs.gig_id = gigs.id WHERE bands.id = ?;");
			
			bandsGigsPSt.setInt(1, bandId);
			
			ResultSet bandsGigsRs = bandsGigsPSt.executeQuery();
			
			PreparedStatement albumsPSt = connection.prepareStatement("SELECT bands.id, bands.name, albums.id, albums.name FROM bands JOIN albums ON albums.band_id = bands.id WHERE bands.id = ?;");
			
			albumsPSt.setInt(1, bandId);

			ResultSet albumsRs = albumsPSt.executeQuery();
			
			while (bandsRs.next()) {
				band.setId(bandsRs.getInt(1));
				band.setName(bandsRs.getString(2));
				List<Album> albums = albumRepository.findAlbumsByBandId(albumsRs);
				band.setAlbums(albums);
				List<Gig> gigs = new ArrayList();
				gigs.addAll(gigRepository.findGigsByBandId(bandsGigsRs));
				band.setGigs(gigs);
			}
			
			bandsRs.close();
			bandsGigsRs.close();
			pst.close();
			bandsGigsPSt.close();
			
		} catch (SQLException e) {
			Logger logger = Logger.getLogger(AlbumRepository.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		} 
		
		return band;
	}
	
	public List<Band> findBandsByGigId(List<GigBandDTO> objectList, int gigId) throws SQLException {
		
		List<Band> bandsByGig = new ArrayList<>();
		
		for (GigBandDTO dto: objectList) {
			Band band = new Band();
			if (dto.getGigId() == gigId) {
				band.setId(dto.getBandId());
				band.setName(dto.getBandName());
				bandsByGig.add(band);
			}
		}
		
		return bandsByGig;
	}
	
	public List<Band> findBandsByGigId(ResultSet rs) throws SQLException {
		
		List<Band> bandsByGig = new ArrayList<>();
		
		while (rs.next()) {
			Band band = new Band();
				band.setId(rs.getInt(3));
				band.setName(rs.getString(4));
				bandsByGig.add(band);
		}
		
		return bandsByGig;
	}

}
