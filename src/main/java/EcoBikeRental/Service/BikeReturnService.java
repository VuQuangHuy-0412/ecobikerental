package EcoBikeRental.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import EcoBikeRental.Dao.BikeRentDao;
import EcoBikeRental.Dao.BikeReturnDao;
import EcoBikeRental.Dao.DepositTransactionDao;
import EcoBikeRental.Dao.DockHasBikeDao;
import EcoBikeRental.Dao.PaymentTransactionDao;
import EcoBikeRental.Entity.BikeRent;
import EcoBikeRental.Entity.BikeReturn;
import EcoBikeRental.Entity.PaymentTransaction;

@Service
public class BikeReturnService {
	@Autowired
	BikeRentDao bikeRentDao;
	
	@Autowired
	BikeReturnDao bikeReturnDao;
	
	@Autowired
	InterbankConnection interbankConnection;
	
	@Autowired
	DepositTransactionDao depositTransactionDao;
	
	@Autowired
	PaymentTransactionDao paymentTransactionDao;
	
	@Autowired
	DockHasBikeDao dockHasBikeDao;
	
	public Long calculatePaymentAmount(Integer bikeId) {
		try {
			BikeRent bikeRent = bikeRentDao.getLastBikeRent().get(0);
			
			String rentTime = bikeRent.getRentTime();
			long allRentTime = calculateTime(rentTime);
			
			if (allRentTime <= 600) {
				return (long) 0;
			} else {
				if (allRentTime <= 1800) {
					return (long) 10000;
				} else {
					return (long) 10000 + ((allRentTime - 1801)/900 + 1)*3000;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Long calculateTime(String rentTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date dateRent = formatter.parse(rentTime);
		
		Calendar c = Calendar.getInstance();
		c.setTime(dateRent);
		long rentTimestamp = c.getTimeInMillis();
		long returnTimestamp = System.currentTimeMillis();
		
		long allRentTime = (returnTimestamp - rentTimestamp)/1000;
		return allRentTime;
	}
	
	public String processReturn(Integer bikeId, Integer dockId, Integer point, Long refundAmount) {
		try {
			
			//save bike return
			BikeReturn bikeReturn = new BikeReturn();
			bikeReturn.setDockId(dockId);
			bikeReturn.setIsPaid(1);
			bikeReturn.setRentId(bikeRentDao.getLastBikeRent().get(0).getRentId());
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now(); 
			bikeReturn.setReturnTime(dtf.format(now).toString());
			
			bikeReturnDao.saveBikeReturn(bikeReturn);
			
			//refund or pay more 
			if (refundAmount > 0) {
				JsonNode result = interbankConnection.processTransaction("refund", refundAmount, "Hoan tien thue xe");
			} else {
				JsonNode result = interbankConnection.processTransaction("pay", -refundAmount, "Tra tien thue xe");
				if (result.get("errorCode").asText().equals("02")) {
					interbankConnection.resetBalance();
					result = interbankConnection.processTransaction("pay", -refundAmount, "Tra tien thue xe");
				}
			}
			
			//save payment transaction
			PaymentTransaction paymentTransaction = new PaymentTransaction();
			paymentTransaction.setCreatedTime(dtf.format(now).toString());
			paymentTransaction.setDepositTransactionId(depositTransactionDao.getDepositTransactionByRentId(bikeRentDao.getLastBikeRent().get(0).getRentId()).getDepositTransactionId());
			paymentTransaction.setPayment(calculatePaymentAmount(bikeId));
			paymentTransaction.setRentId(bikeRentDao.getLastBikeRent().get(0).getRentId());
			paymentTransaction.setReturnedMoney(refundAmount);
			paymentTransaction.setTime(calculateTime(bikeRentDao.getLastBikeRent().get(0).getRentTime()));
			
			paymentTransactionDao.savePaymentTransaction(paymentTransaction);
			
			//update dock_has_bike
			dockHasBikeDao.updateBikeDock(dockId, 1, bikeId, point);
			
			return "Pay and return bike success!";
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
