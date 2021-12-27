package EcoBikeRental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import EcoBikeRental.Service.BikeRentService;
import EcoBikeRental.Service.BikeReturnService;
import EcoBikeRental.Service.BikeService;
import EcoBikeRental.Service.DockService;

/**
 * Description: Controller manage the action of returning bike 
 *
 */
@Controller
public class BikeReturnController {
	
	@Autowired
	BikeService bikeService;
	
	@Autowired
	DockService dockService;
	
	@Autowired
	BikeRentService bikeRentService;
	
	@Autowired
	BikeReturnService bikeReturnService;
	
	/**
	 * Description: method add data bike, category, listDocks show to the view return_bike
	 * @return ModelAndView: Model to show to view and redirect to the view return_bike.jsp
	 */
	@RequestMapping(value = "/return-bike", method = RequestMethod.GET)
	public ModelAndView returnBike() {
		ModelAndView mav = new ModelAndView("return_bike");
		
		Integer bikeId = bikeService.getCurrentBikeId();
		mav.addObject("bikeId", bikeId);
		
		//check that if you are renting a bike or not
		if (bikeId != -1) {
			mav.addObject("bike", bikeService.getBikeByBikeId(bikeId));
			mav.addObject("category", bikeService.getCategoryByBikeId(bikeId));
			mav.addObject("listDocks", dockService.getAllDock());
		}
		
		return mav;
	}
	
	/**
	 * Description: method add data bike, category, dock, paymentAmount show to the view confirm_return_bike
	 * @param bikeId: id of the bike want to return
	 * @param dockId: id of the dock you want to return to
	 * @return ModelAndView: Model to show to view and redirect to the view confirm_return_bike.jsp
	 */
	@RequestMapping(value = "/confirm-return-bike", method = RequestMethod.GET)
	public ModelAndView confirmReturnBike(@RequestParam("bikeId") Integer bikeId, @RequestParam("dockId") Integer dockId) { 
		ModelAndView mav = new ModelAndView("confirm_return_bike");
		
		mav.addObject("dock", dockService.getDockByDockId(dockId));
		mav.addObject("bike", bikeService.getBikeByBikeId(bikeId));
		mav.addObject("category", bikeService.getCategoryByBikeId(bikeId));
		mav.addObject("paymentAmount", bikeReturnService.getPaymentAmount());
		
		return mav;
	}
	
	/**
	 * @param bikeId: id of the bike want to return
	 * @param dockId: id of the dock you want to return to
	 * @param point: the point you want to return to
	 * @param refundAmount: the money system return to renter
	 * @return ModelAndView: Model to show to view and redirect to the view return_bike.jsp
	 */
	@RequestMapping(value = "/process-return", method = RequestMethod.GET) 
	public ModelAndView processReturn(@RequestParam("bikeId") Integer bikeId, @RequestParam("dockId") Integer dockId, @RequestParam("point") Integer point, @RequestParam("refundAmount") Long refundAmount) {
		ModelAndView mav = new ModelAndView("process_return");
		
		mav.addObject("status", bikeReturnService.processReturn(bikeId, dockId, point, refundAmount));
		
		return mav;
	}
}
