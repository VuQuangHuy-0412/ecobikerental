package EcoBikeRental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import EcoBikeRental.Entity.DockHasBike;
import EcoBikeRental.Service.BikeRentService;
import EcoBikeRental.Service.BikeService;
import EcoBikeRental.Service.DockService;

@Controller
public class BikeRentController {
	@Autowired
	BikeService bikeService;
	
	@Autowired
	DockService dockService;
	
	@Autowired
	BikeRentService bikeRentService;
	
	@RequestMapping(value = "/confirm-rent-bike", method = RequestMethod.GET)
	public ModelAndView comfirmRentBike(@RequestParam("bikeId") Integer bikeId) {
		ModelAndView mav = new ModelAndView("rent_bike");
		mav.addObject("bike", bikeService.getBikeByBikeId(bikeId));
		mav.addObject("category", bikeService.getCategoryByBikeId(bikeId));
		mav.addObject("dock", dockService.getDockByDockId(bikeService.getBikeByBikeId(bikeId).getDockId()));
		mav.addObject("barcode", Integer.toBinaryString(bikeId));
//		mav.addObject("numberOfBikeCategory", bikeService.getNumberBikeCategoryByDockId(dockId));
		return mav;
	}
	
	@RequestMapping(value = "/rent-bike", method = RequestMethod.GET)
	public ModelAndView rentBike() {
		ModelAndView mav = new ModelAndView("choose_bike_rent");
		Integer bikeId = bikeService.getCurrentBikeId();
		mav.addObject("bikeId", bikeId);
		return mav;
	}
	
	@RequestMapping(value = "/rent-bike-with-barcode", method = RequestMethod.GET)
	public ModelAndView rentBike(@RequestParam("barcode") String barcode) {
		ModelAndView mav = new ModelAndView();
		DockHasBike bike = bikeService.getBikeByBarcode(barcode);
		if (bike != null) {
			mav.setViewName("rent_bike");
			Integer bikeId = bike.getBikeId();
			mav.addObject("bike", bikeService.getBikeByBikeId(bikeId));
			mav.addObject("category", bikeService.getCategoryByBikeId(bikeId));
			mav.addObject("dock", dockService.getDockByDockId(bikeService.getBikeByBikeId(bikeId).getDockId()));
			mav.addObject("barcode", Integer.toBinaryString(bikeId));
		} else {
			mav.setViewName("not_found_bike");
		}
		return mav;
	}
	
	@RequestMapping(value = "/process-rent", method = RequestMethod.GET)
	public ModelAndView processRent(@RequestParam("bikeId") Integer bikeId) {
		ModelAndView mav = new ModelAndView("process-rent");
		mav.addObject("status", bikeRentService.processRent(bikeId));
		return mav;
	}
}
