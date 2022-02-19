package EcoBikeRental.Service;

import org.springframework.stereotype.Service;

import EcoBikeRental.Entity.BikeCategory;

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
	public Long calculatePaymentAmount(Long allRentTime, BikeCategory category) {
		long value;
		// if rent time < 10 mins, fee = 0
		// if rent time < 30 mins, fee = 10000
		// if rent time > 30 mins, fee += 3000 each 15 mins
		if (allRentTime <= 600) {
			value = (long) 0;
		} else {
			if (allRentTime <= 1800) {
				value = (long) 10000;
			} else {
				value = (long) 10000 + ((allRentTime - 1801)/900 + 1)*3000;
			}
		}
		
		int categoryId = category.getCategoryId();
		float coefficient = category.getCoefficient();
		
		if (categoryId == 1) 
			return value;
		else if (categoryId == 2 || categoryId == 3) 
			return (long) coefficient*value;
		
		return null;
		
	}
}
