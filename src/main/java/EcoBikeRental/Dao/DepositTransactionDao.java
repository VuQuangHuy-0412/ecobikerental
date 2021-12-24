package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperDepositTransaction;
import EcoBikeRental.Entity.DepositTransaction;

/**
 * Description: class Dao execute the query to table deposit_transaction
 *
 */
@Repository
public class DepositTransactionDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Description: execute the query get all deposit transaction
	 * @return List<DepositTransaction>: list deposit transaction received after execute query
	 */
	public List<DepositTransaction> getAllDepositTransaction() {
		List<DepositTransaction> listDepositTransactions = new ArrayList<DepositTransaction>();
		String sql = "SELECT * FROM deposit_transaction";
		listDepositTransactions = jdbcTemplate.query(sql, new MapperDepositTransaction());
		return listDepositTransactions;
	}
	
	/**
	 * Description: execute the query get deposit transaction by rent id
	 * @param rentId: id of the rent you want to get deposit transaction
	 * @return DepositTransaction: deposit transaction received after execute query
	 */
	public DepositTransaction getDepositTransactionByRentId(Integer rentId) {
		DepositTransaction depositTransaction = new DepositTransaction();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM deposit_transaction dt where dt.rent_id = ").append(rentId);
		String sql = builder.toString();
		depositTransaction = jdbcTemplate.queryForObject(sql, new MapperDepositTransaction());
		return depositTransaction;
	}
	
	/**
	 * Description: execute the query save deposit transaction
	 * @param depositTransaction: info of deposit transaction want to save
	 * @return DepositTransaction: deposit transaction after save to db
	 */
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
