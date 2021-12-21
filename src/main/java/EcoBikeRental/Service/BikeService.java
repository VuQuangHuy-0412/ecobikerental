package EcoBikeRental.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcoBikeRental.Dao.BikeCategoryDao;
import EcoBikeRental.Dao.DockHasBikeDao;
import EcoBikeRental.Dto.NumberOfBikeCategoryDto;
import EcoBikeRental.Entity.BikeCategory;
import EcoBikeRental.Entity.DockHasBike;

@Service
public class BikeService {
	@Autowired
	DockHasBikeDao dockHasBikeDao;
	
	@Autowired
	BikeCategoryDao bikeCategoryDao;
	
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
}
