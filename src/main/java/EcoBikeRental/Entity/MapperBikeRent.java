package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperBikeRent implements RowMapper<BikeRent>{

	public BikeRent mapRow(ResultSet rs, int rowNum) throws SQLException {
		BikeRent bikeRent = new BikeRent();
		bikeRent.setRentId(rs.getInt("rent_id"));
		bikeRent.setUserId(rs.getInt("user_id"));
		bikeRent.setBikeId(rs.getInt("bike_id"));
		bikeRent.setRentTime(rs.getString("rent_time"));
		bikeRent.setIsDeposited(rs.getInt("is_deposited"));
		bikeRent.setDockId(rs.getInt("dock_id"));
		return bikeRent;
	}

}
