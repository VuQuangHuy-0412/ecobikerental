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
		String sql = "SELECT * FROM DockHasBike";
		listDockHasBikes = jdbcTemplate.query(sql, new MapperDockHasBike());
		return listDockHasBikes;
	}
}
