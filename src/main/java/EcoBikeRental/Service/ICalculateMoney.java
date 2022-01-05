package EcoBikeRental.Service;

import java.text.ParseException;

/**
 * Interface calculate money
 *
 */
public interface ICalculateMoney {
	public Long calculatePaymentAmount(Long allRentTime);

	public Long calculateTime(String rentTime) throws ParseException;
}
