package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperBikeReturn;
import EcoBikeRental.Entity.BikeReturn;

@Repository
public class BikeReturnDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<BikeReturn> getAllBikeReturn() {
		List<BikeReturn> listBikeReturns = new ArrayList<BikeReturn>();
		String sql = "SELECT * FROM BikeReturn";
		listBikeReturns = jdbcTemplate.query(sql, new MapperBikeReturn());
		return listBikeReturns;
	}
}
