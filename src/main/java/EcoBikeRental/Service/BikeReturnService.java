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

/**
 * Description: Class Service to execute the action logic return bike
 *
 */
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
	
	/**
	 * Description: method calculate the money of a rent transaction
	 * @param bikeId: id of the bike renting
	 * @return Long: money of the rent transaction
	 */
	public Long calculatePaymentAmount(Integer bikeId) {
		try {
			BikeRent bikeRent = bikeRentDao.getLastBikeRent().get(0);
			
			// get rent time and return time
			String rentTime = bikeRent.getRentTime();
			long allRentTime = calculateTime(rentTime);
			
			// if rent time < 10 mins, fee = 0
			// if rent time < 30 mins, fee = 10000
			// if rent time > 30 mins, fee += 3000 each 15 mins
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
	
	/**
	 * Description: method calculate the time of a rent transaction
	 * @param rentTime: the start rent time
	 * @return Long: the time from start to end rent bike (seconds)
	 * @throws ParseException
	 */
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
	
	/**
	 * Description: method process return a bike
	 * @param bikeId: id of the bike returning
	 * @param dockId: id of the dock want to renting
	 * @param point: point want to renting
	 * @param refundAmount: the refund money system give to user
	 * @return String: status of process: success or fail
	 */
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
