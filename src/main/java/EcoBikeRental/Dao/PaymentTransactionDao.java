package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.DepositTransaction;
import EcoBikeRental.Entity.MapperPaymentTransaction;
import EcoBikeRental.Entity.PaymentTransaction;

/**
 * Description: class Dao execute the query to table payment_transaction
 *
 */
@Repository
public class PaymentTransactionDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * Description: execute the query get all payment_transaction
	 * @return List<PaymentTransaction>: list bike return received after execute query
	 */
	public List<PaymentTransaction> getAllPaymentTransaction() {
		List<PaymentTransaction> listPaymentTransactions = new ArrayList<PaymentTransaction>();
		String sql = "SELECT * FROM payment_transaction";
		listPaymentTransactions = jdbcTemplate.query(sql, new MapperPaymentTransaction());
		return listPaymentTransactions;
	}
	
	/**
	 * Description: execute the query save payment_transaction
	 * @param paymentTransaction: info of payment transaction you want to save
	 * @return PaymentTransaction: payment transaction after save to db
	 */
	public PaymentTransaction savePaymentTransaction(PaymentTransaction paymentTransaction) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO payment_transaction (rent_id, time, payment, deposit_transaction_id, returned_money, created_time) VALUES (")
			.append(paymentTransaction.getRentId()).append(",")
			.append(paymentTransaction.getTime()).append(",")
			.append(paymentTransaction.getPayment()).append(",")
			.append(paymentTransaction.getDepositTransactionId()).append(",")
			.append(paymentTransaction.getReturnedMoney()).append(",'")
			.append(paymentTransaction.getCreatedTime()).append("')");
		String sql = builder.toString();
		jdbcTemplate.update(sql);
		return paymentTransaction;
	}
}
