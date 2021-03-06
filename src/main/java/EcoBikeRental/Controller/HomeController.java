package EcoBikeRental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import EcoBikeRental.Service.BikeRentService;
import EcoBikeRental.Service.DockService;

/**
 * Description: Controller manage the action of home
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	DockService dockService;
	
	@Autowired
	BikeRentService bikeRentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("show_list_dock");
		
		mav.addObject("provinces", dockService.getListDockProvince());
		mav.addObject("docks", dockService.getAllDock());
		
		return mav;
	}
}
