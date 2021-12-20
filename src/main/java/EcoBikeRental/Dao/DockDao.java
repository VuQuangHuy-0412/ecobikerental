package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperDock;
import EcoBikeRental.Entity.Dock;

@Repository
public class DockDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Dock> getAllDock() {
		List<Dock> listDocks = new ArrayList<Dock>();
		String sql = "SELECT * FROM dock";
		listDocks = jdbcTemplate.query(sql, new MapperDock());
		return listDocks;
	}
	
	public List<Dock> getListDockProvince() {
		List<Dock> listProvince = new ArrayList<Dock>();
		String sql = "Select * from dock d group by d.province;";
		listProvince = jdbcTemplate.query(sql, new MapperDock());
		return listProvince;
	}
}
