package com.trch.repository;

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
import com.trch.dto.GigDTO;
import com.trch.model.Album;
import com.trch.model.Band;
import com.trch.model.Gig;

public class GigRepository {
	
public List<Gig> findAllGigs() {
		
		List<Gig> gigs = new ArrayList<>();
				
		try (Connection connection = DbConfig.getConnection()) {
			
			PreparedStatement allGigsPSt = connection.prepareStatement
					("SELECT gigs.id, gigs.name FROM gigs;");
			
			PreparedStatement gigsBandsPSt = connection.prepareStatement("SELECT gigs.id, gigs.name, bands.id, bands.name FROM gigs JOIN bands_gigs ON bands_gigs.gig_id = gigs.id JOIN bands ON bands_gigs.band_id = bands.id;");
			
			ResultSet gigsRs = allGigsPSt.executeQuery();
			
			ResultSet gigsBandsRs = gigsBandsPSt.executeQuery();
			
			BandRepository bandRepository = new BandRepository();
						
			List<GigDTO> gigsRs_list = new ArrayList<>();
			
			while (gigsRs.next()) {
				GigDTO dto = new GigDTO(gigsRs.getInt(1), gigsRs.getString(2));
				gigsRs_list.add(dto);
			}
			gigsRs.close();
			allGigsPSt.close();
			
			List<GigBandDTO> gigsBandsRs_list = new ArrayList<>();
			while (gigsBandsRs.next()) {
				GigBandDTO dto = new GigBandDTO(gigsBandsRs.getInt(1), gigsBandsRs.getString(2), gigsBandsRs.getInt(3), gigsBandsRs.getString(4));
				gigsBandsRs_list.add(dto);
			} 
			
			gigsBandsRs.close();
			gigsBandsPSt.close();
			
			for (GigDTO dto : gigsRs_list) {
				System.out.println(dto.toString());
				Gig gig = new Gig();
				gig.setId(dto.getGigId());
				gig.setName(dto.getGigName());
				List<Band> bands = bandRepository.findBandsByGigId(gigsBandsRs_list, gig.getId());
				gig.setBands(bands);
				gigs.add(gig);
			}
			
		} catch (SQLException e) {
			Logger logger = Logger.getLogger(AlbumRepository.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		} 
		
		return gigs;
}



	public List<Gig> findGigsByBandId(ResultSet rs) throws SQLException {
			
		List<Gig> gigsByBand = new ArrayList<>();
		
		while (rs.next()) {
			Gig gig = new Gig();
				gig.setId(rs.getInt(3));
				gig.setName(rs.getString(4));
				gigsByBand.add(gig);
		}
		
		return gigsByBand;
	}
	
public List<Gig> findGigsByBandId(List<BandGigDTO> objectList, int bandId) throws SQLException {
		
		List<Gig> gigsByBand = new ArrayList<>();
		
		for (BandGigDTO dto: objectList) {
			Gig gig = new Gig();
			if (dto.getBandId() == bandId) {
				gig.setId(dto.getGigId());
				gig.setName(dto.getGigName());
				gigsByBand.add(gig);
			}
			} 
	
		return gigsByBand;
}

	
	public Gig findGigByGigId(int gigId) {
		
		Gig gig = new Gig();
		
		try (Connection connection = DbConfig.getConnection())
		{			
			PreparedStatement pst = connection.prepareStatement
					("SELECT * FROM gigs WHERE gigs.id = ?;");
			pst.setInt(1, gigId);
			
			ResultSet gigsRs = pst.executeQuery();
			
			PreparedStatement gigsBandsPSt = connection.prepareStatement("SELECT gigs.id, gigs.name, bands.id, bands.name FROM gigs JOIN bands_gigs ON bands_gigs.gig_id = gigs.id JOIN bands ON bands_gigs.band_id = bands.id WHERE gigs.id = ?;");
			
			gigsBandsPSt.setInt(1, gigId);
			
			ResultSet gigsBandsRs = gigsBandsPSt.executeQuery();
			
			BandRepository bandRepository = new BandRepository();

			
			while (gigsRs.next()) {
				gig.setId(gigsRs.getInt(1));
				gig.setName(gigsRs.getString(2));
				List<Band> bands = new ArrayList();
				bands.addAll(bandRepository.findBandsByGigId(gigsBandsRs));
				gig.setBands(bands);
			}
			
			gigsRs.close();
			gigsBandsRs.close();
			pst.close();
			gigsBandsPSt.close();
			
		} catch (SQLException e) {
			Logger logger = Logger.getLogger(AlbumRepository.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
		} 
		
		return gig;
	
	}

	

}
