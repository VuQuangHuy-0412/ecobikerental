package EcoBikeRental.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcoBikeRental.Dao.BikeCategoryDao;
import EcoBikeRental.Dao.BikeRentDao;
import EcoBikeRental.Dao.BikeReturnDao;
import EcoBikeRental.Dao.BikeDao;
import EcoBikeRental.Dto.NumberOfBikeCategoryDto;
import EcoBikeRental.Entity.BikeCategory;
import EcoBikeRental.Entity.BikeRent;
import EcoBikeRental.Entity.BikeReturn;
import EcoBikeRental.Entity.Dock;
import EcoBikeRental.Entity.Bike;

/**
 * Description: Class Service execute the logic action of bike 
 *
 */
@Service
public class BikeService {
	@Autowired
	BikeDao dockHasBikeDao;
	
	@Autowired
	BikeCategoryDao bikeCategoryDao;
	
	@Autowired
	BikeReturnDao bikeReturnDao;
	
	@Autowired
	BikeRentDao bikeRentDao;
	
	public List<Bike> getListBikeByDock(Dock dock) {
		try {
			List<Bike> listBikes = new ArrayList<Bike>();
			listBikes = dockHasBikeDao.getListBikeByDock(dock);
			return listBikes;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<NumberOfBikeCategoryDto> getNumberBikeCategoryByDock(Dock dock) {
		try {
			List<NumberOfBikeCategoryDto> listCount = new ArrayList<NumberOfBikeCategoryDto>();
			List<BikeCategory> listCategory = bikeCategoryDao.getAllBikeCategory();
			
			// count number of each category bike
			for (BikeCategory e : listCategory) {
				NumberOfBikeCategoryDto numberOfBikeCategoryDto = new NumberOfBikeCategoryDto();
				numberOfBikeCategoryDto.setBikeCategory(e);
				List<Bike> listBikes = dockHasBikeDao.getListBikeByDock(dock);
				int count = 0;
				
				for (Bike bike : listBikes) {
					if (bike.getBikeCategory().getCategoryId() == e.getCategoryId()) {
						count += 1;
					}
				}
				numberOfBikeCategoryDto.setNumbers(count);
				listCount.add(numberOfBikeCategoryDto);
			}
			
			return listCount;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Bike getBikeByBikeId(Integer bikeId) {
		try {
			Bike bike = dockHasBikeDao.getBikeByBikeId(bikeId);
			
			return bike;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Bike getBikeByBarcode(String barcode) {
		try {
			Bike bike = dockHasBikeDao.getBikeByBarcode(barcode);
			
			return bike;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Integer getCurrentBikeId() {
		List<BikeRent> listBikeRent = bikeRentDao.getLastBikeRent();
		if (listBikeRent.isEmpty() == false) {
			List<BikeReturn> bikeReturn = bikeReturnDao.getBikeReturnByRentId(listBikeRent.get(0).getRentId());
			if (bikeReturn.isEmpty() == false) {
				return -1;
			} else {
				return listBikeRent.get(0).getBike().getBikeId();
			}
		} else {
			return -1;
		}
	}
}
