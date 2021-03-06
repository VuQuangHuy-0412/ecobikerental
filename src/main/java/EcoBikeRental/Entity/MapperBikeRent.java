package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import EcoBikeRental.Dao.BikeDao;
import EcoBikeRental.Dao.DockDao;

public class MapperBikeRent implements RowMapper<BikeRent>{
	
	@Autowired
	BikeDao bikeDao;
	
	@Autowired
	DockDao dockDao;

	public BikeRent mapRow(ResultSet rs, int rowNum) throws SQLException {
		BikeRent bikeRent = new BikeRent();
		bikeRent.setRentId(rs.getInt("rent_id"));
		bikeRent.setUserId(rs.getInt("user_id"));
		bikeRent.setBike(bikeDao.getBikeByBikeId(rs.getInt("bike_id")));
		bikeRent.setRentTime(rs.getString("rent_time"));
		bikeRent.setIsDeposited(rs.getInt("is_deposited"));
		bikeRent.setDock(dockDao.getDockByDockId(rs.getInt("dock_id")));
		return bikeRent;
	}

}
