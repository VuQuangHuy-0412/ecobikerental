package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.DepositTransaction;
import EcoBikeRental.Entity.MapperPaymentTransaction;
import EcoBikeRental.Entity.PaymentTransaction;

@Repository
public class PaymentTransactionDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<PaymentTransaction> getAllPaymentTransaction() {
		List<PaymentTransaction> listPaymentTransactions = new ArrayList<PaymentTransaction>();
		String sql = "SELECT * FROM payment_transaction";
		listPaymentTransactions = jdbcTemplate.query(sql, new MapperPaymentTransaction());
		return listPaymentTransactions;
	}
	
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
