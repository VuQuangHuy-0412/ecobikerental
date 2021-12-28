package EcoBikeRental.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	IInterbankConnection interbankConnection;
	
	@Autowired
	DepositTransactionDao depositTransactionDao;
	
	@Autowired
	PaymentTransactionDao paymentTransactionDao;
	
	@Autowired
	DockHasBikeDao dockHasBikeDao;
	
	@Autowired
	ICalculateMoney calculateMoney;
	
	/**
	 * Description: method calculate the money of a rent transaction
	 * @return Long: money of the rent transaction
	 */
	public Long getPaymentAmount() {
		try {
			BikeRent bikeRent = bikeRentDao.getLastBikeRent().get(0);
			
			// get rent time and return time
			String rentTime = bikeRent.getRentTime();
			long allRentTime = calculateMoney.calculateTime(rentTime);
			
			return calculateMoney.calculatePaymentAmount(allRentTime);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Description: method process return a bike
	 * @param bikeId: id of the bike returning
	 * @param dockId: id of the dock want to renting
	 * @param point: point want to renting
	 * @param refundAmount: the refund money system give to user
	 * @return String: status of process: success or fail
	 */
	public String processReturn(Integer bikeId, Integer dockId, Integer point, Long refundAmount, String cardCode, String owner) {
		try {
			JsonNode result;
			//refund or pay more 
			if (refundAmount > 0) {
				result = interbankConnection.processTransaction("refund", refundAmount, "Hoan tien thue xe", cardCode, owner);
			} else {
				result = interbankConnection.processTransaction("pay", -refundAmount, "Tra tien thue xe", cardCode, owner);
				if (result.get("errorCode").asText().equals("02")) {
					interbankConnection.resetBalance(cardCode, owner);
					result = interbankConnection.processTransaction("pay", -refundAmount, "Tra tien thue xe", cardCode, owner);
				}
			}
			
			if (result.get("errorCode").asText() != "00") {
				return "Card Information Invalid";
			} else {
				//save bike return
				BikeReturn bikeReturn = new BikeReturn();
				bikeReturn.setDockId(dockId);
				bikeReturn.setIsPaid(1);
				bikeReturn.setRentId(bikeRentDao.getLastBikeRent().get(0).getRentId());
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now(); 
				bikeReturn.setReturnTime(dtf.format(now).toString());
				
				bikeReturnDao.saveBikeReturn(bikeReturn);
				
				//save payment transaction
				PaymentTransaction paymentTransaction = new PaymentTransaction();
				paymentTransaction.setCreatedTime(dtf.format(now).toString());
				paymentTransaction.setDepositTransactionId(depositTransactionDao.getDepositTransactionByRentId(bikeRentDao.getLastBikeRent().get(0).getRentId()).getDepositTransactionId());
				paymentTransaction.setPayment(getPaymentAmount());
				paymentTransaction.setRentId(bikeRentDao.getLastBikeRent().get(0).getRentId());
				paymentTransaction.setReturnedMoney(refundAmount);
				paymentTransaction.setTime(calculateMoney.calculateTime(bikeRentDao.getLastBikeRent().get(0).getRentTime()));
				
				paymentTransactionDao.savePaymentTransaction(paymentTransaction);
				
				//update dock_has_bike
				dockHasBikeDao.updateBikeDock(dockId, 1, bikeId, point);
				
				return "Pay and return bike success!";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
