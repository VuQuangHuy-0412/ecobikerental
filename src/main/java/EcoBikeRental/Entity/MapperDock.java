package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperDock implements RowMapper<Dock>{

	public Dock mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dock dock = new Dock();
		dock.setDockId(rs.getInt("dock_id"));
		dock.setName(rs.getString("name"));
		dock.setAddress(rs.getString("address"));
		dock.setDescription(rs.getString("description"));
		dock.setCreatedAt(rs.getString("created_at"));
		dock.setUpdatedAt(rs.getString("updated_at"));
		return dock;
	}

}
