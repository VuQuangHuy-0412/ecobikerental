package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperBikeReturn;
import EcoBikeRental.Entity.BikeReturn;

/**
 * Description: class Dao execute the query to table bike_return
 *
 */
@Repository
public class BikeReturnDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Description: execute the query get all bike return
	 * @return List<BikeReturn>: list bike return received after execute query
	 */
	public List<BikeReturn> getAllBikeReturn() {
		List<BikeReturn> listBikeReturns = new ArrayList<BikeReturn>();
		String sql = "SELECT * FROM bike_return";
		listBikeReturns = jdbcTemplate.query(sql, new MapperBikeReturn());
		return listBikeReturns;
	}
	
	/**
	 * Description: execute the query get list bike return by rent_id
	 * @param rentId: id of bike rent want to get
	 * @return List<BikeReturn>: list bike return received after execute query
	 */
	public List<BikeReturn> getBikeReturnByRentId(Integer rentId) {
		List<BikeReturn> bikeReturn = new ArrayList<BikeReturn>();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM bike_return br WHERE br.rent_id = ").append(rentId);
		String sql = builder.toString();
		bikeReturn = jdbcTemplate.query(sql, new MapperBikeReturn());
		return bikeReturn;
	}
	
	/**
	 * Description: execute the query save bike return
	 * @param bikeReturn: info of bike return want to save
	 * @return BikeReturn: bike return after save to db
	 */
	public BikeReturn saveBikeReturn(BikeReturn bikeReturn) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO bike_return (rent_id, return_time, dock_id, is_paid) VALUES (")
			.append(bikeReturn.getRent().getRentId()).append(",'")
			.append(bikeReturn.getReturnTime()).append("',")
			.append(bikeReturn.getDock().getDockId()).append(",")
			.append(bikeReturn.getIsPaid()).append(")");
		String sql = builder.toString();
		jdbcTemplate.update(sql);
		return bikeReturn;
	}
}
