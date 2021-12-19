package EcoBikeRental.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import EcoBikeRental.Entity.MapperPaymentTransaction;
import EcoBikeRental.Entity.PaymentTransaction;

@Repository
public class PaymentTransactionDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<PaymentTransaction> getAllPaymentTransaction() {
		List<PaymentTransaction> listPaymentTransactions = new ArrayList<PaymentTransaction>();
		String sql = "SELECT * FROM PaymentTransaction";
		listPaymentTransactions = jdbcTemplate.query(sql, new MapperPaymentTransaction());
		return listPaymentTransactions;
	}
}
