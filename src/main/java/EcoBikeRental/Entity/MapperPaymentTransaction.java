package EcoBikeRental.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import EcoBikeRental.Dao.BikeRentDao;
import EcoBikeRental.Dao.DepositTransactionDao;

public class MapperPaymentTransaction implements RowMapper<PaymentTransaction>{
	
	@Autowired
	DepositTransactionDao depositTransactionDao;
	
	@Autowired
	BikeRentDao bikeRentDao;

	public PaymentTransaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		PaymentTransaction paymentTransaction = new PaymentTransaction();
		paymentTransaction.setCreatedTime(rs.getString("created_time"));
		paymentTransaction.setDepositTransaction(depositTransactionDao.getDepositTransactionById(rs.getInt("deposit_transaction_id")));
		paymentTransaction.setPayment(rs.getLong("payment"));
		paymentTransaction.setPaymentTransactionId(rs.getInt("payment_transaction_id"));
		paymentTransaction.setRent(bikeRentDao.getBikeRentById(rs.getInt("rent_id")));
		paymentTransaction.setReturnedMoney(rs.getLong("returned_money"));
		paymentTransaction.setTime(rs.getLong("time"));
		return paymentTransaction;
	}

}
