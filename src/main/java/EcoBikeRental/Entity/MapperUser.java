package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperUser implements RowMapper<User>{

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setFullName(rs.getString("full_name"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setStatus(rs.getInt("status"));
		user.setCreatedAt(rs.getString("created_at"));
		user.setUpdatedAt(rs.getString("updated_at"));
		user.setIsRenting(rs.getInt("is_renting"));
		return user;
	}

}
