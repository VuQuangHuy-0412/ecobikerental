package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperDepositTransaction implements RowMapper<DepositTransaction>{

	public DepositTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		DepositTransaction depositTransaction = new DepositTransaction();
		depositTransaction.setDepositTransactionId(rs.getInt("deposit_transaction_id"));
		depositTransaction.setRentId(rs.getInt("rent_id"));
		depositTransaction.setMoneyAmount(rs.getLong("money_amount"));
		depositTransaction.setCreatedAt(rs.getString("created_at"));
		depositTransaction.setDepositBy(rs.getString("deposit_by"));
		depositTransaction.setDescription(rs.getString("description"));
		depositTransaction.setStatus(rs.getString("status"));
		return depositTransaction;
	}

}
