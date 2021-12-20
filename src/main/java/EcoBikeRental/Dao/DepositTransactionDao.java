package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperDepositTransaction;
import EcoBikeRental.Entity.DepositTransaction;

@Repository
public class DepositTransactionDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<DepositTransaction> getAllDepositTransaction() {
		List<DepositTransaction> listDepositTransactions = new ArrayList<DepositTransaction>();
		String sql = "SELECT * FROM deposit_transaction";
		listDepositTransactions = jdbcTemplate.query(sql, new MapperDepositTransaction());
		return listDepositTransactions;
	}
}
