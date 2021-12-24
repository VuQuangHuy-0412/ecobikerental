package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperBikeReturn;
import EcoBikeRental.Entity.BikeRent;
import EcoBikeRental.Entity.BikeReturn;

@Repository
public class BikeReturnDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<BikeReturn> getAllBikeReturn() {
		List<BikeReturn> listBikeReturns = new ArrayList<BikeReturn>();
		String sql = "SELECT * FROM bike_return";
		listBikeReturns = jdbcTemplate.query(sql, new MapperBikeReturn());
		return listBikeReturns;
	}
	
	public List<BikeReturn> getBikeReturnByRentId(Integer rentId) {
		List<BikeReturn> bikeReturn = new ArrayList<BikeReturn>();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM bike_return br WHERE br.rent_id = ").append(rentId);
		String sql = builder.toString();
		bikeReturn = jdbcTemplate.query(sql, new MapperBikeReturn());
		return bikeReturn;
	}
	
	public BikeReturn saveBikeReturn(BikeReturn bikeReturn) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO bike_return (rent_id, return_time, dock_id, is_paid) VALUES (")
			.append(bikeReturn.getRentId()).append(",'")
			.append(bikeReturn.getReturnTime()).append("',")
			.append(bikeReturn.getDockId()).append(",")
			.append(bikeReturn.getIsPaid()).append(")");
		String sql = builder.toString();
		jdbcTemplate.update(sql);
		return bikeReturn;
	}
}
