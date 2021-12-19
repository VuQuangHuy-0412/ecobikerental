package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperPaymentTransaction implements RowMapper<PaymentTransaction>{

	public PaymentTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		PaymentTransaction paymentTransaction = new PaymentTransaction();
		paymentTransaction.setCreatedTime(rs.getString("created_time"));
		paymentTransaction.setDepositTransactionId(rs.getInt("deposit_transaction_id"));
		paymentTransaction.setPayment(rs.getLong("payment"));
		paymentTransaction.setPaymentTransactionId(rs.getInt("payment_transaction_id"));
		paymentTransaction.setRentId(rs.getInt("rent_id"));
		paymentTransaction.setReturnedMoney(rs.getLong("returned_money"));
		paymentTransaction.setTime(rs.getInt("time"));
		return paymentTransaction;
	}

}
