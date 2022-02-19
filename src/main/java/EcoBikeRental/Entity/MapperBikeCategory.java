package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperBikeCategory implements RowMapper<BikeCategory>{

	public BikeCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		BikeCategory bikeCategory = new BikeCategory();
		bikeCategory.setCategoryId(rs.getInt("category_id"));
		bikeCategory.setName(rs.getString("name"));
		bikeCategory.setDescription(rs.getString("description"));
		bikeCategory.setPrice(rs.getInt("price"));
		bikeCategory.setImage(rs.getString("image"));
		bikeCategory.setCoefficient(rs.getFloat("coefficient"));
		return bikeCategory;
	}

}
