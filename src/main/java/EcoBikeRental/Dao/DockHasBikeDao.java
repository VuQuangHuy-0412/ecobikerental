package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperDockHasBike;
import EcoBikeRental.Entity.DockHasBike;

@Repository
public class DockHasBikeDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<DockHasBike> getAllDockHasBike() {
		List<DockHasBike> listDockHasBikes = new ArrayList<DockHasBike>();
		String sql = "SELECT * FROM dock_has_bike";
		listDockHasBikes = jdbcTemplate.query(sql, new MapperDockHasBike());
		return listDockHasBikes;
	}
	
	public List<DockHasBike> getListBikeByDockId(Integer dockId) {
		List<DockHasBike> listDockHasBikes = new ArrayList<DockHasBike>();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM dock_has_bike dhb WHERE dhb.dock_id = ").append(dockId).append(" AND dhb.active = 1;");
		String sql = builder.toString();
		listDockHasBikes = jdbcTemplate.query(sql, new MapperDockHasBike());
		return listDockHasBikes;
	}
	
	public DockHasBike getBikeByBikeId(Integer bikeId) {
		DockHasBike bike = new DockHasBike();
		StringBuilder builder = new StringBuilder();
		builder.append("Select * from dock_has_bike dhb where dhb.bike_id = ").append(bikeId).append(" AND dhb.active = 1;");
		String sql = builder.toString();
		bike = jdbcTemplate.queryForObject(sql, new MapperDockHasBike());
		return bike;
	}
	
	public DockHasBike getBikeByBarcode(String barcode) {
		DockHasBike bike = new DockHasBike();
		StringBuilder builder = new StringBuilder();
		builder.append("Select * from dock_has_bike dhb where dhb.barcode LIKE '").append(barcode).append("' AND dhb.active = 1;");
		String sql = builder.toString();
		bike = jdbcTemplate.queryForObject(sql, new MapperDockHasBike());
		return bike;
	}
}
