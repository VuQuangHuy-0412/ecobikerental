package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperDockHasBike implements RowMapper<DockHasBike>{

	public DockHasBike mapRow(ResultSet rs, int rowNum) throws SQLException {
		DockHasBike dockHasBike = new DockHasBike();
		dockHasBike.setId(rs.getInt("id"));
		dockHasBike.setBikeId(rs.getInt("bike_id"));
		dockHasBike.setCategoryId(rs.getInt("category_id"));
		dockHasBike.setDescription(rs.getString("description"));
		dockHasBike.setStatus(rs.getInt("status"));
		dockHasBike.setDockId(rs.getInt("dock_id"));
		return dockHasBike;
	}

}
