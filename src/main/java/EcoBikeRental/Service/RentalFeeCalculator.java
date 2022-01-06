package EcoBikeRental.Service;

import org.springframework.stereotype.Service;

/**
 * Calculate money version 1
 *
 */
@Service
public class RentalFeeCalculator implements RentalFeeCalculatorInterface{
	
	/**
	 * @param allRentTime
	 * @return
	 */
	public Long calculatePaymentAmount(Long allRentTime) {
		
		// if rent time < 10 mins, fee = 0
		// if rent time < 30 mins, fee = 10000
		// if rent time > 30 mins, fee += 3000 each 15 mins
		if (allRentTime <= 600) {
			return (long) 0;
		} else {
			if (allRentTime <= 1800) {
				return (long) 10000;
			} else {
				return (long) 10000 + ((allRentTime - 1801)/900 + 1)*3000;
			}
		}
	}
}
