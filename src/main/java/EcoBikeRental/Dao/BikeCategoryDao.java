package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.BikeCategory;
import EcoBikeRental.Entity.MapperBikeCategory;

/**
 * Description: class Dao execute the query to table bike_category
 *
 */
@Repository
public class BikeCategoryDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Description: execute the query get all bike category
	 * @return List<BikeCategory>: list category received after execute query
	 */
	public List<BikeCategory> getAllBikeCategory() {
		List<BikeCategory> listBikeCategorys = new ArrayList<BikeCategory>();
		String sql = "SELECT * FROM bike_category";
		listBikeCategorys = jdbcTemplate.query(sql, new MapperBikeCategory());
		return listBikeCategorys;
	}
	
	/**
	 * Description: execute the query get bike category by id
	 * @param categoryId: id of category want to get
	 * @return BikeCategory: category received after execute query
	 */
	public BikeCategory getCategoryById(Integer categoryId) {
		BikeCategory category = new BikeCategory();
		StringBuilder builder = new StringBuilder();
		builder.append("Select * from bike_category bc where bc.category_id = ").append(categoryId);
		String sql = builder.toString();
		category = jdbcTemplate.queryForObject(sql, new MapperBikeCategory());
		return category;
	}
}
