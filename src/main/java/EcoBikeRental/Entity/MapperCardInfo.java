package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperCardInfo implements RowMapper<CardInfo>{

	public CardInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		CardInfo cardInfo = new CardInfo();
		cardInfo.setUserId(rs.getInt("user_id"));
		cardInfo.setCardId(rs.getInt("card_id"));
		cardInfo.setCardNumber(rs.getString("card_number"));
		cardInfo.setBank(rs.getString("bank"));
		cardInfo.setCreatedAt(rs.getString("created_at"));
		cardInfo.setUpdatedAt(rs.getString("updated_at"));
		return cardInfo;
	}

}
