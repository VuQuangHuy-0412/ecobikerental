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

/**
 * Description: Controller manage the action of renting bike 
 *
 */
@Controller
public class BikeRentController {
	@Autowired
	BikeService bikeService;
	
	@Autowired
	DockService dockService;
	
	@Autowired
	BikeRentService bikeRentService;
	
	/**
	 * Description: method add data bike, category, dock show to the view rent_bike
	 * @param bikeId: id of the bike confirm to rent
	 * @return ModelAndView: Model to show to view and redirect to the view rent_bike.jsp
	 */
	@RequestMapping(value = "/confirm-rent-bike", method = RequestMethod.GET)
	public ModelAndView comfirmRentBike(@RequestParam("bikeId") Integer bikeId) {
		ModelAndView mav = new ModelAndView("rent_bike");
		
		//add bike detail to show to view
		mav.addObject("bike", bikeService.getBikeByBikeId(bikeId));
		mav.addObject("category", bikeService.getCategoryByBikeId(bikeId));
		mav.addObject("dock", dockService.getDockByDockId(bikeService.getBikeByBikeId(bikeId).getDockId()));
		mav.addObject("barcode", Integer.toBinaryString(bikeId));
//		mav.addObject("numberOfBikeCategory", bikeService.getNumberBikeCategoryByDockId(dockId));
		
		return mav;
	}
	
	/**
	 * Description: method add data bikeId show to the view choose_bike_rent
	 * @return ModelAndView: Model to show to view and redirect to the view choose_bike_rent.jsp
	 */
	@RequestMapping(value = "/rent-bike", method = RequestMethod.GET)
	public ModelAndView rentBike() {
		ModelAndView mav = new ModelAndView("choose_bike_rent");
		Integer bikeId = bikeService.getCurrentBikeId();
		mav.addObject("bikeId", bikeId);
		return mav;
	}
	
	/**
	 * Description: method add data bike, category, dock show to the view rent_bike
	 * @param barcode: the barcode of the bike you want to rent
	 * @return ModelAndView: Model to show to view and redirect to the view rent_bike.jsp
	 */
	@RequestMapping(value = "/rent-bike-with-barcode", method = RequestMethod.GET)
	public ModelAndView rentBike(@RequestParam("barcode") String barcode) {
		ModelAndView mav = new ModelAndView();
		DockHasBike bike = bikeService.getBikeByBarcode(barcode);
		
		//check that you are renting bike or not
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
	
	/**
	 * @param bikeId: the id of the bike you want to rent
	 * @return ModelAndView: Model to show to view and redirect to the view process-rent.jsp
	 */
	@RequestMapping(value = "/process-rent", method = RequestMethod.GET)
	public ModelAndView processRent(@RequestParam("bikeId") Integer bikeId, @RequestParam("cardCode") String cardCode, @RequestParam("owner") String owner) {
		ModelAndView mav = new ModelAndView("process-rent");
		
		mav.addObject("status", bikeRentService.processRent(bikeId, cardCode, owner));
		
		return mav;
	}
}
