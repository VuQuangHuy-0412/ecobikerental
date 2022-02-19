package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import EcoBikeRental.Dao.BikeRentDao;
import EcoBikeRental.Dao.DockDao;

public class MapperBikeReturn implements RowMapper<BikeReturn>{
	@Autowired
	BikeRentDao bikeRentDao;
	
	@Autowired
	DockDao dockDao;

	public BikeReturn mapRow(ResultSet rs, int rowNum) throws SQLException {
		BikeReturn bikeReturn = new BikeReturn();
		bikeReturn.setReturnId(rs.getInt("return_id"));
		bikeReturn.setRent(bikeRentDao.getBikeRentById(rs.getInt("rent_id")));
		bikeReturn.setReturnTime(rs.getString("return_time"));
		bikeReturn.setDock(dockDao.getDockByDockId(rs.getInt("dock_id")));
		bikeReturn.setIsPaid(rs.getInt("is_paid"));
		return bikeReturn;
	}

}
