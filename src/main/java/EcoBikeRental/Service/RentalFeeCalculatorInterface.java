package EcoBikeRental.Service;

/**
 * Interface calculate money
 *
 */
public interface RentalFeeCalculatorInterface {
	public Long calculatePaymentAmount(Long allRentTime);
}
