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
	
	public DepositTransaction getDepositTransactionByRentId(Integer rentId) {
		DepositTransaction depositTransaction = new DepositTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM deposit_transaction dt where dt.rent_id = ").append(rentId);
		String sql = builder.toString();
		depositTransaction = jdbcTemplate.queryForObject(sql, new MapperDepositTransaction());
		return depositTransaction;
	}
	
	public DepositTransaction saveDepositTransaction(DepositTransaction depositTransaction) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO deposit_transaction (rent_id, money_amount, created_at, description, status, deposit_by) VALUES (")
			.append(depositTransaction.getRentId()).append(",")
			.append(depositTransaction.getMoneyAmount()).append(",'")
			.append(depositTransaction.getCreatedAt()).append("','")
			.append(depositTransaction.getDescription()).append("','")
			.append(depositTransaction.getStatus()).append("','")
			.append(depositTransaction.getDepositBy()).append("')");
		String sql = builder.toString();
		jdbcTemplate.update(sql);
		return depositTransaction;
	}
}
