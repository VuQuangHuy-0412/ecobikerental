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
		String sql = "SELECT * FROM bike_rent";
		listBikeRents = jdbcTemplate.query(sql, new MapperBikeRent());
		return listBikeRents;
	}
	
	public BikeRent saveBikeRent(BikeRent bikeRent) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO bike_rent (user_id, bike_id, rent_time, is_deposited, dock_id) VALUES (")
			.append(bikeRent.getUserId()).append(",")
			.append(bikeRent.getBikeId()).append(",'")
			.append(bikeRent.getRentTime()).append("',")
			.append(bikeRent.getIsDeposited()).append(",")
			.append(bikeRent.getDockId()).append(")");
		String sql = builder.toString();
		jdbcTemplate.update(sql);
		return bikeRent;
	}
	
	public BikeRent getLastBikeRent() {
		BikeRent bikeRent = new BikeRent();
		String sql = "SELECT * FROM bike_rent br ORDER BY br.rent_id DESC LIMIT 1;";
		bikeRent = jdbcTemplate.queryForObject(sql, new MapperBikeRent());
		return bikeRent;
	}
}
