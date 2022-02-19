package EcoBikeRental.Service;

import EcoBikeRental.Entity.BikeCategory;

/**
 * Interface calculate money
 *
 */
public interface RentalFeeCalculatorInterface {
	public Long calculatePaymentAmount(Long allRentTime, BikeCategory bikeCategory);
}
