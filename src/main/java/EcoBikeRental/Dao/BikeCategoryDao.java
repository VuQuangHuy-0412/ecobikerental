package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.BikeCategory;
import EcoBikeRental.Entity.MapperBikeCategory;

@Repository
public class BikeCategoryDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<BikeCategory> getAllBikeCategory() {
		List<BikeCategory> listBikeCategorys = new ArrayList<BikeCategory>();
		String sql = "SELECT * FROM bike_category";
		listBikeCategorys = jdbcTemplate.query(sql, new MapperBikeCategory());
		return listBikeCategorys;
	}
}
