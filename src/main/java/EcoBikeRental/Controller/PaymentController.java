package EcoBikeRental.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {
	@RequestMapping(value = "/add-card-deposit", method = RequestMethod.GET)
	public ModelAndView addCardInfoDeposit(@RequestParam("bikeId") Integer bikeId) {
		ModelAndView mav = new ModelAndView("add_card_info_deposit");
		
		mav.addObject("bikeId", bikeId);
		
		return mav;
	}
	
	@RequestMapping(value = "/add-card-payment", method = RequestMethod.GET)
	public ModelAndView addCardInfoPayment(@RequestParam("bikeId") Integer bikeId, @RequestParam("dockId") Integer dockId, @RequestParam("point") Integer point, @RequestParam("refundAmount") Long refundAmount) {
		ModelAndView mav = new ModelAndView("add_card_info_payment");
		
		mav.addObject("bikeId", bikeId);
		mav.addObject("dockId", dockId);
		mav.addObject("point", point);
		mav.addObject("refundAmount", refundAmount);
		
		return mav;
	}
}
