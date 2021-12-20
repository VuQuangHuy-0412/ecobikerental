package EcoBikeRental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import EcoBikeRental.Service.DockService;

@Controller
public class DockController {
	@Autowired
	DockService dockService;
	
	@RequestMapping(value = "/list-dock", method = RequestMethod.GET)
	public ModelAndView showListDocks() {
		ModelAndView mav = new ModelAndView("show_list_dock");
		mav.addObject("provinces", dockService.getListDockProvince());
		mav.addObject("docks", dockService.getAllDock());
		return mav;
	}
}
