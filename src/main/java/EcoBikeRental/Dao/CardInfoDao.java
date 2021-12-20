package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperCardInfo;
import EcoBikeRental.Entity.CardInfo;

@Repository
public class CardInfoDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<CardInfo> getAllCardInfo() {
		List<CardInfo> listCardInfos = new ArrayList<CardInfo>();
		String sql = "SELECT * FROM card_info";
		listCardInfos = jdbcTemplate.query(sql, new MapperCardInfo());
		return listCardInfos;
	}
}
