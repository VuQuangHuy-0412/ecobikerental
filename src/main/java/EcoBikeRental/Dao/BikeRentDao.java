package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.BikeRent;
import EcoBikeRental.Entity.MapperBikeRent;

@Repository
public class BikeRentDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<BikeRent> getAllBikeRent() {
		List<BikeRent> listBikeRents = new ArrayList<BikeRent>();
		String sql = "SELECT * FROM user";
		listBikeRents = jdbcTemplate.query(sql, new MapperBikeRent());
		return listBikeRents;
	}
}
