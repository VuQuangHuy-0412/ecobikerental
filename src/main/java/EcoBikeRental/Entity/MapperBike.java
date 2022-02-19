package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import EcoBikeRental.Dao.BikeCategoryDao;
import EcoBikeRental.Dao.DockDao;

public class MapperBike implements RowMapper<Bike>{
	
	@Autowired
	DockDao dockDao;
	
	@Autowired
	BikeCategoryDao categoryDao;

	public Bike mapRow(ResultSet rs, int rowNum) throws SQLException {
		Bike dockHasBike = new Bike();
		dockHasBike.setId(rs.getInt("id"));
		dockHasBike.setBikeId(rs.getInt("bike_id"));
		dockHasBike.setBikeCategory(categoryDao.getCategoryById(rs.getInt("category_id")));
		dockHasBike.setDescription(rs.getString("description"));
		dockHasBike.setStatus(rs.getInt("status"));
		dockHasBike.setDock(dockDao.getDockByDockId(rs.getInt("dock_id")));
		dockHasBike.setActive(rs.getInt("active"));
		dockHasBike.setPoint(rs.getInt("point"));
		dockHasBike.setBarcode(rs.getString("barcode"));
		return dockHasBike;
	}

}
