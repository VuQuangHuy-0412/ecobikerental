package EcoBikeRental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import EcoBikeRental.Entity.Bike;
import EcoBikeRental.Service.BikeService;
import EcoBikeRental.Service.DockService;

/**
 * Description: class bike controller manage the action of bike
 *
 */
@Controller
public class BikeController {
	@Autowired
	BikeService bikeService;
	
	@Autowired
	DockService dockService;
	
	/**
	 * Description: method show bike detail of BikeController
	 * @param bikeId: id of the bike to show 
	 * @return ModelAndView: Model to show to view and redirect to the view bike_detail.jsp
	 */
	@RequestMapping(value = "/bike-detail", method = RequestMethod.GET)
	public ModelAndView showBikeDetail(@RequestParam("bikeId") Integer bikeId) {
		ModelAndView mav = new ModelAndView("bike_detail");
		
		Bike bike = bikeService.getBikeByBikeId(bikeId);
		
		//add data bike, category, dock and barcode to show view
		mav.addObject("bike", bike);
		
		return mav;
	}
	
	/**
	 * Description: method show bike detail you are renting of BikeController
	 * @return ModelAndView: Model to show to view and redirect to the view current_bike_status.jsp
	 */
	@RequestMapping(value = "/current-bike-status", method = RequestMethod.GET)
	public ModelAndView showBikeDetail() {
		ModelAndView mav = new ModelAndView();
		Integer bikeId = bikeService.getCurrentBikeId();
		mav.addObject("bikeId", bikeId);
		
		Bike bike = bikeService.getBikeByBikeId(bikeId);
		//check that you are renting bike or not
		if (bikeId != -1) {
			mav.addObject("bike", bike);
		} 
		
		mav.setViewName("current_bike_status");
		return mav;
	}
}
