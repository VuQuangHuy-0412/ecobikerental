package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperBikeReturn implements RowMapper<BikeReturn>{

	public BikeReturn mapRow(ResultSet rs, int rowNum) throws SQLException {
		BikeReturn bikeReturn = new BikeReturn();
		bikeReturn.setReturnId(rs.getInt("return_id"));
		bikeReturn.setRentId(rs.getInt("rent_id"));
		bikeReturn.setReturnTime(rs.getString("return_time"));
		bikeReturn.setDockId(rs.getInt("dock_id"));
		bikeReturn.setIsPaid(rs.getInt("is_paid"));
		return bikeReturn;
	}

}
