package EcoBikeRental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import EcoBikeRental.Dao.HomeDao;

@Controller
public class HomeController {
	@Autowired
	HomeDao homeDao;

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("users", homeDao.getAllUser());
		return mav;
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView product() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
}
