package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperUser;
import EcoBikeRental.Entity.User;

@Repository
public class HomeDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<User> getAllUser() {
		List<User> listUsers = new ArrayList<User>();
		String sql = "SELECT * FROM user";
		listUsers = jdbcTemplate.query(sql, new MapperUser());
		return listUsers;
	}
}
