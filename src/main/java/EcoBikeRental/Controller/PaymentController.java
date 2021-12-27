package EcoBikeRental.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {
	@RequestMapping(value = "/add-card", method = RequestMethod.GET)
	public ModelAndView addCardInfo() {
		ModelAndView mav = new ModelAndView("add_card_info");
		
		return mav;
	}
}
