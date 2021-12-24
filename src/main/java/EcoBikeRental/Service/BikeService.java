package EcoBikeRental.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcoBikeRental.Dao.BikeCategoryDao;
import EcoBikeRental.Dao.BikeRentDao;
import EcoBikeRental.Dao.BikeReturnDao;
import EcoBikeRental.Dao.DockHasBikeDao;
import EcoBikeRental.Dto.NumberOfBikeCategoryDto;
import EcoBikeRental.Entity.BikeCategory;
import EcoBikeRental.Entity.BikeReturn;
import EcoBikeRental.Entity.DockHasBike;

@Service
public class BikeService {
	@Autowired
	DockHasBikeDao dockHasBikeDao;
	
	@Autowired
	BikeCategoryDao bikeCategoryDao;
	
	@Autowired
	BikeReturnDao bikeReturnDao;
	
	@Autowired
	BikeRentDao bikeRentDao;
	
	public List<DockHasBike> getListBikeByDockId(Integer dockId) {
		try {
			List<DockHasBike> listBikes = new ArrayList<DockHasBike>();
			listBikes = dockHasBikeDao.getListBikeByDockId(dockId);
			return listBikes;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<NumberOfBikeCategoryDto> getNumberBikeCategoryByDockId(Integer dockId) {
		try {
			List<NumberOfBikeCategoryDto> listCount = new ArrayList<NumberOfBikeCategoryDto>();
			List<BikeCategory> listCategory = bikeCategoryDao.getAllBikeCategory();
			
			for (BikeCategory e : listCategory) {
				NumberOfBikeCategoryDto numberOfBikeCategoryDto = new NumberOfBikeCategoryDto();
				numberOfBikeCategoryDto.setBikeCategory(e);
				List<DockHasBike> listBikes = dockHasBikeDao.getListBikeByDockId(dockId);
				int count = 0;
				
				for (DockHasBike bike : listBikes) {
					if (bike.getCategoryId() == e.getCategoryId()) {
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
	
	public DockHasBike getBikeByBikeId(Integer bikeId) {
		try {
			DockHasBike bike = dockHasBikeDao.getBikeByBikeId(bikeId);
			
			return bike;
		} catch (Exception e) {
			return null;
		}
	}
	
	public BikeCategory getCategoryByBikeId(Integer bikeId) {
		try {
			DockHasBike bike = getBikeByBikeId(bikeId);
			BikeCategory category = bikeCategoryDao.getCategoryById(bike.getCategoryId());
			return category;
		} catch (Exception e) {
			return null;
		}
	}
	
	public DockHasBike getBikeByBarcode(String barcode) {
		try {
			DockHasBike bike = dockHasBikeDao.getBikeByBarcode(barcode);
			
			return bike;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Integer getCurrentBikeId() {
		if (bikeRentDao.getLastBikeRent().isEmpty() == false) {
			List<BikeReturn> bikeReturn = bikeReturnDao.getBikeReturnByRentId(bikeRentDao.getLastBikeRent().get(0).getRentId());
			if (bikeReturn.isEmpty() == false) {
				return -1;
			} else {
				return bikeRentDao.getLastBikeRent().get(0).getBikeId();
			}
		} else {
			return -1;
		}
	}
}
