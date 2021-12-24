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
	
	@RequestMapping(value = "/return-bike", method = RequestMethod.GET)
	public ModelAndView returnBike() {
		ModelAndView mav = new ModelAndView("return_bike");
		Integer bikeId = bikeService.getCurrentBikeId();
		mav.addObject("bikeId", bikeId);
		if (bikeId != -1) {
			mav.addObject("bike", bikeService.getBikeByBikeId(bikeId));
			mav.addObject("category", bikeService.getCategoryByBikeId(bikeId));
			mav.addObject("listDocks", dockService.getAllDock());
		}
		return mav;
	}
	
	@RequestMapping(value = "/confirm-return-bike", method = RequestMethod.GET)
	public ModelAndView confirmReturnBike(@RequestParam("bikeId") Integer bikeId, @RequestParam("dockId") Integer dockId) { 
		ModelAndView mav = new ModelAndView("confirm_return_bike");
		mav.addObject("dock", dockService.getDockByDockId(dockId));
		mav.addObject("bike", bikeService.getBikeByBikeId(bikeId));
		mav.addObject("category", bikeService.getCategoryByBikeId(bikeId));
		mav.addObject("paymentAmount", bikeReturnService.calculatePaymentAmount(bikeId));
		return mav;
	}
	
	@RequestMapping(value = "/process-return", method = RequestMethod.GET) 
	public ModelAndView processReturn(@RequestParam("bikeId") Integer bikeId, @RequestParam("dockId") Integer dockId, @RequestParam("point") Integer point, @RequestParam("refundAmount") Long refundAmount) {
		ModelAndView mav = new ModelAndView("process_return");
		mav.addObject("status", bikeReturnService.processReturn(bikeId, dockId, point, refundAmount));
		return mav;
	}
}
