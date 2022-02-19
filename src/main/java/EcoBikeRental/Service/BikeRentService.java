package EcoBikeRental.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import EcoBikeRental.Dao.BikeRentDao;
import EcoBikeRental.Dao.BikeReturnDao;
import EcoBikeRental.Dao.DepositTransactionDao;
import EcoBikeRental.Dao.BikeDao;
import EcoBikeRental.Entity.BikeRent;
import EcoBikeRental.Entity.DepositTransaction;
import EcoBikeRental.Entity.Bike;
import EcoBikeRental.Entity.BikeCategory;
import EcoBikeRental.Subsystem.InterbankInterface;

/**
 * Description: Class Service to execute the action logic rent bike
 *
 */
@Service
public class BikeRentService {
	@Autowired
	BikeRentDao bikeRentDao;

	@Autowired
	BikeService bikeService;

	@Autowired
	InterbankInterface interbankConnection;

	@Autowired
	DepositTransactionDao depositTransactionDao;

	@Autowired
	BikeReturnDao bikeReturnDao;

	@Autowired
	BikeDao dockHasBikeDao;

	/**
	 * Description: process the request rent bike
	 * 
	 * @param bikeId: id of the bike want to rent
	 * @return String: status success or fail
	 */
	public String processRent(Bike bike, String cardCode, String owner) {
		try {
			int checked = bikeService.getCurrentBikeId();
			if (checked == -1) {
				BikeCategory category = bike.getBikeCategory();
				// connect interbank to deposit, if balance is not enough, reset balance
				JsonNode result = interbankConnection.processTransaction("pay", (long) category.getPrice(),
						"Dat coc thue xe", cardCode, owner);
				if (result.get("errorCode").asText().equals("02")) {
					interbankConnection.resetBalance(cardCode, owner);
					result = interbankConnection.processTransaction("pay", (long) category.getPrice(),
							"Dat coc thue xe", cardCode, owner);
				}
				if (result.get("errorCode").asText().equals("00")) {
					// save bike rent
					BikeRent bikeRent = new BikeRent();
					bikeRent.setUserId(1);
					bikeRent.setBike(bike);
					bikeRent.setDock(bike.getDock());
					bikeRent.setIsDeposited(1);

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					bikeRent.setRentTime(dtf.format(now).toString());
					
					bikeRentDao.saveBikeRent(bikeRent);
					//query DB again to get the last record because jdbc doesn't support save and get id directly
					bikeRent = bikeRentDao.getLastBikeRent().get(0);

					// save deposit transaction
					DepositTransaction depositTransaction = new DepositTransaction();
					depositTransaction.setCreatedAt(dtf.format(LocalDateTime.now()).toString());
					depositTransaction.setDepositBy("INTERBANK");
					depositTransaction.setDescription("Dat coc thue xe");
					depositTransaction.setMoneyAmount((long) category.getPrice());
					depositTransaction.setRent(bikeRent);
					depositTransaction.setStatus("SUCCESS");

					depositTransactionDao.saveDepositTransaction(depositTransaction);

					// set this bike is inactive
					dockHasBikeDao.updateBikeDock(bike.getDock(), 0, bike, bike.getPoint());

					return "Deposit success! You can take the bike and use.";
				} else {
					return "Card Information Invalid";
				}
			} else {
				return "You are renting. You must return the bike if you want to rent another.";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
