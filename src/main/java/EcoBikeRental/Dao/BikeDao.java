package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperBike;
import EcoBikeRental.Entity.Bike;
import EcoBikeRental.Entity.Dock;

/**
 * Description: class Dao execute the query to table bike
 *
 */
@Repository
public class BikeDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Description: execute the query get all bike
	 * @return List<DockHasBike>: list bike received after execute query
	 */
	public List<Bike> getAllDockHasBike() {
		List<Bike> listDockHasBikes = new ArrayList<Bike>();
		String sql = "SELECT * FROM bike";
		listDockHasBikes = jdbcTemplate.query(sql, new MapperBike());
		return listDockHasBikes;
	}
	
	/**
	 * Description: execute the query get all bike of a dock
	 * @param dockId: id of the dock want to get bike
	 * @return List<DockHasBike>: list bike of the dock received after execute query
	 */
	public List<Bike> getListBikeByDock(Dock dock) {
		List<Bike> listDockHasBikes = new ArrayList<Bike>();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM bike dhb WHERE dhb.dock_id = ").append(dock.getDockId()).append(" AND dhb.active = 1;");
		String sql = builder.toString();
		listDockHasBikes = jdbcTemplate.query(sql, new MapperBike());
		return listDockHasBikes;
	}
	
	/**
	 * Description: execute the query get bike by id
	 * @param bikeId: id of the bike want to get
	 * @return DockHasBike: the bike received after execute query
	 */
	public Bike getBikeByBikeId(Integer bikeId) {
		Bike bike = new Bike();
		StringBuilder builder = new StringBuilder();
		builder.append("Select * from bike dhb where dhb.bike_id = ").append(bikeId);
		String sql = builder.toString();
		bike = jdbcTemplate.queryForObject(sql, new MapperBike());
		return bike;
	}
	
	/**
	 * Description: execute the query get bike by barcode
	 * @param barcode: the barcode of the bike want to get
	 * @return DockHasBike: the bike received after execute query
	 */
	public Bike getBikeByBarcode(String barcode) {
		Bike bike = new Bike();
		StringBuilder builder = new StringBuilder();
		builder.append("Select * from bike dhb where dhb.barcode LIKE '").append(barcode).append("' AND dhb.active = 1;");
		String sql = builder.toString();
		bike = jdbcTemplate.queryForObject(sql, new MapperBike());
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
	public Bike updateBikeDock(Dock dock, Integer active, Bike bike, Integer point) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE bike dhb SET dhb.active = ").append(active).append(", dhb.dock_id = ").append(dock.getDockId()).append(", dhb.point = ").append(point).append(" WHERE dhb.bike_id = ").append(bike.getBikeId());
		String sql = builder.toString();
		jdbcTemplate.update(sql);
		return bike;
	}
}
