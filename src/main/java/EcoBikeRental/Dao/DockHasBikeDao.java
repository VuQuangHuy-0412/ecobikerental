package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperDockHasBike;
import EcoBikeRental.Entity.DockHasBike;

/**
 * Description: class Dao execute the query to table dock_has_bike
 *
 */
@Repository
public class DockHasBikeDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Description: execute the query get all bike
	 * @return List<DockHasBike>: list bike received after execute query
	 */
	public List<DockHasBike> getAllDockHasBike() {
		List<DockHasBike> listDockHasBikes = new ArrayList<DockHasBike>();
		String sql = "SELECT * FROM dock_has_bike";
		listDockHasBikes = jdbcTemplate.query(sql, new MapperDockHasBike());
		return listDockHasBikes;
	}
	
	/**
	 * Description: execute the query get all bike of a dock
	 * @param dockId: id of the dock want to get bike
	 * @return List<DockHasBike>: list bike of the dock received after execute query
	 */
	public List<DockHasBike> getListBikeByDockId(Integer dockId) {
		List<DockHasBike> listDockHasBikes = new ArrayList<DockHasBike>();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM dock_has_bike dhb WHERE dhb.dock_id = ").append(dockId).append(" AND dhb.active = 1;");
		String sql = builder.toString();
		listDockHasBikes = jdbcTemplate.query(sql, new MapperDockHasBike());
		return listDockHasBikes;
	}
	
	/**
	 * Description: execute the query get bike by id
	 * @param bikeId: id of the bike want to get
	 * @return DockHasBike: the bike received after execute query
	 */
	public DockHasBike getBikeByBikeId(Integer bikeId) {
		DockHasBike bike = new DockHasBike();
		StringBuilder builder = new StringBuilder();
		builder.append("Select * from dock_has_bike dhb where dhb.bike_id = ").append(bikeId);
		String sql = builder.toString();
		bike = jdbcTemplate.queryForObject(sql, new MapperDockHasBike());
		return bike;
	}
	
	/**
	 * Description: execute the query get bike by barcode
	 * @param barcode: the barcode of the bike want to get
	 * @return DockHasBike: the bike received after execute query
	 */
	public DockHasBike getBikeByBarcode(String barcode) {
		DockHasBike bike = new DockHasBike();
		StringBuilder builder = new StringBuilder();
		builder.append("Select * from dock_has_bike dhb where dhb.barcode LIKE '").append(barcode).append("' AND dhb.active = 1;");
		String sql = builder.toString();
		bike = jdbcTemplate.queryForObject(sql, new MapperDockHasBike());
		return bike;
	}
	
	/**
	 * Description: execute the query update bike
	 * @param dockId: new dock id of the bike
	 * @param active: new status of the bike
	 * @param bikeId: id of the bike
	 * @param point: the new point of the bike
	 * @return DockHasBike: the bike received after execute query
	 */
	public DockHasBike updateBikeDock(Integer dockId, Integer active, Integer bikeId, Integer point) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE dock_has_bike dhb SET dhb.active = ").append(active).append(", dhb.dock_id = ").append(dockId).append(", dhb.point = ").append(point).append(" WHERE dhb.bike_id = ").append(bikeId);
		String sql = builder.toString();
		jdbcTemplate.update(sql);
		return getBikeByBikeId(bikeId);
	}
}
