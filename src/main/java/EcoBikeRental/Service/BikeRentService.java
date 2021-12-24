package EcoBikeRental.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import EcoBikeRental.Dao.BikeRentDao;
import EcoBikeRental.Dao.BikeReturnDao;
import EcoBikeRental.Dao.DepositTransactionDao;
import EcoBikeRental.Dao.DockHasBikeDao;
import EcoBikeRental.Entity.BikeRent;
import EcoBikeRental.Entity.BikeReturn;
import EcoBikeRental.Entity.DepositTransaction;
import EcoBikeRental.Entity.DockHasBike;

@Service
public class BikeRentService {
	@Autowired
	BikeRentDao bikeRentDao;
	
	@Autowired
	BikeService bikeService;
	
	@Autowired
	InterbankConnection interbankConnection;
	
	@Autowired
	DepositTransactionDao depositTransactionDao;
	
	@Autowired
	BikeReturnDao bikeReturnDao;
	
	@Autowired
	DockHasBikeDao dockHasBikeDao;
	
	public String processRent(Integer bikeId) {
		try {
			if (bikeRentDao.getLastBikeRent().isEmpty() == false) {
				List<BikeReturn> bikeReturn = bikeReturnDao.getBikeReturnByRentId(bikeRentDao.getLastBikeRent().get(0).getRentId());
				if (bikeReturn.isEmpty() == false) {
					// save bike rent
					BikeRent bikeRent = new BikeRent();
					bikeRent.setUserId(1);
					bikeRent.setBikeId(bikeId);
					DockHasBike bike = bikeService.getBikeByBikeId(bikeId);
					bikeRent.setDockId(bike.getDockId());
					bikeRent.setIsDeposited(1);
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
					LocalDateTime now = LocalDateTime.now(); 
					bikeRent.setRentTime(dtf.format(now).toString());
					
					bikeRentDao.saveBikeRent(bikeRent);
					bikeRent = bikeRentDao.getLastBikeRent().get(0);
					
					// connect interbank to deposit, if balance is not enough, reset balance
					JsonNode result = interbankConnection.processTransaction("pay", (long) bikeService.getCategoryByBikeId(bikeId).getPrice(), "Dat coc thue xe");
					if (result.get("errorCode").asText().equals("02")) {
						interbankConnection.resetBalance();
						result = interbankConnection.processTransaction("pay", (long) bikeService.getCategoryByBikeId(bikeId).getPrice(), "Dat coc thue xe");
					}
					
					// save deposit transaction
					DepositTransaction depositTransaction = new DepositTransaction();
					depositTransaction.setCreatedAt(dtf.format(LocalDateTime.now()).toString());
					depositTransaction.setDepositBy("INTERBANK");
					depositTransaction.setDescription("Dat coc thue xe");
					depositTransaction.setMoneyAmount((long) bikeService.getCategoryByBikeId(bikeId).getPrice());
					depositTransaction.setRentId(bikeRent.getRentId());
					depositTransaction.setStatus("SUCCESS");
					
					depositTransactionDao.saveDepositTransaction(depositTransaction);
					
					//set this bike is inactive
					dockHasBikeDao.updateBikeDock(bike.getDockId(), 0, bikeId, bike.getPoint());
					
					return "Deposit success! You can take the bike and use.";
				} else {
					return "You are renting. You must return the bike if you want to rent another.";
				}
			} else {
				// save bike rent
				BikeRent bikeRent = new BikeRent();
				bikeRent.setUserId(1);
				bikeRent.setBikeId(bikeId);
				DockHasBike bike = bikeService.getBikeByBikeId(bikeId);
				bikeRent.setDockId(bike.getDockId());
				bikeRent.setIsDeposited(1);
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now(); 
				bikeRent.setRentTime(dtf.format(now).toString());
				
				bikeRentDao.saveBikeRent(bikeRent);
				bikeRent = bikeRentDao.getLastBikeRent().get(0);
				
				// connect interbank to deposit, if balance is not enough, reset balance
				JsonNode result = interbankConnection.processTransaction("pay", (long) bikeService.getCategoryByBikeId(bikeId).getPrice(), "Dat coc thue xe");
				if (result.get("errorCode").asText().equals("02")) {
					interbankConnection.resetBalance();
					result = interbankConnection.processTransaction("pay", (long) bikeService.getCategoryByBikeId(bikeId).getPrice(), "Dat coc thue xe");
				}
				
				// save deposit transaction
				DepositTransaction depositTransaction = new DepositTransaction();
				depositTransaction.setCreatedAt(dtf.format(LocalDateTime.now()).toString());
				depositTransaction.setDepositBy("INTERBANK");
				depositTransaction.setDescription("Dat coc thue xe");
				depositTransaction.setMoneyAmount((long) bikeService.getCategoryByBikeId(bikeId).getPrice());
				depositTransaction.setRentId(bikeRent.getRentId());
				depositTransaction.setStatus("SUCCESS");
				
				depositTransactionDao.saveDepositTransaction(depositTransaction);
				
				//set this bike is inactive
				dockHasBikeDao.updateBikeDock(bike.getDockId(), 0, bikeId, bike.getPoint());
				
				return "Deposit success! You can take the bike and use.";
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
