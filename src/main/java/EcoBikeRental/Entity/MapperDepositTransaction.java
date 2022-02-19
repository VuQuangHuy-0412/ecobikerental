package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import EcoBikeRental.Dao.BikeRentDao;

public class MapperDepositTransaction implements RowMapper<DepositTransaction>{
	
	@Autowired
	BikeRentDao bikeRentDao;

	public DepositTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		DepositTransaction depositTransaction = new DepositTransaction();
		depositTransaction.setDepositTransactionId(rs.getInt("deposit_transaction_id"));
		depositTransaction.setRent(bikeRentDao.getBikeRentById(rs.getInt("rent_id")));
		depositTransaction.setMoneyAmount(rs.getLong("money_amount"));
		depositTransaction.setCreatedAt(rs.getString("created_at"));
		depositTransaction.setDepositBy(rs.getString("deposit_by"));
		depositTransaction.setDescription(rs.getString("description"));
		depositTransaction.setStatus(rs.getString("status"));
		return depositTransaction;
	}

}
