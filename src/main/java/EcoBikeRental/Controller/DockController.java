package EcoBikeRental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import EcoBikeRental.Service.BikeService;
import EcoBikeRental.Service.DockService;

/**
 * Description: Controller manage the action of dock
 *
 */
@Controller
public class DockController {
	@Autowired
	DockService dockService;
	
	@Autowired
	BikeService bikeService;
	
	/**
	 * Description: method add data provinces, docks show to the view show_list_dock
	 * @return ModelAndView: Model to show to view and redirect to the view show_list_dock.jsp
	 */
	@RequestMapping(value = "/list-dock", method = RequestMethod.GET)
	public ModelAndView showListDocks() {
		ModelAndView mav = new ModelAndView("show_list_dock");
		
		mav.addObject("provinces", dockService.getListDockProvince());
		mav.addObject("docks", dockService.getAllDock());
		
		return mav;
	}
	
	/**
	 * Description: method add data provinces, docksByKeyword show to the view show_list_search_dock
	 * @param keyword: the keyword you want to search dock
	 * @return ModelAndView: Model to show to view and redirect to the view show_list_search_dock.jsp
	 */
	@RequestMapping(value = "/search-dock", method = RequestMethod.GET)
	public ModelAndView showListDocksByKeyword(@RequestParam("keyword") String keyword) {
		ModelAndView mav = new ModelAndView("show_list_search_dock");
		
		mav.addObject("provinces", dockService.getListDockProvince());
		mav.addObject("docksByKeyword", dockService.getListDockByKeyword(keyword));
		
		return mav;
	}
	
	/**
	 * Description: method add data dock, listBikes, numberOdBikeCategory show to the view dock_detail
	 * @param dockId: id of the dock you want to show detail
	 * @return ModelAndView: Model to show to view and redirect to the view dock_detail.jsp
	 */
	@RequestMapping(value = "/dock-detail", method = RequestMethod.GET)
	public ModelAndView showDockDetail(@RequestParam("dockId") Integer dockId) {
		ModelAndView mav = new ModelAndView("dock_detail");
		
		mav.addObject("dock", dockService.getDockByDockId(dockId));
		mav.addObject("listBikes", bikeService.getListBikeByDockId(dockId));
		mav.addObject("numberOfBikeCategory", bikeService.getNumberBikeCategoryByDockId(dockId));
		
		return mav;
	}
}
