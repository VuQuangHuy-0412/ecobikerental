package EcoBikeRental.Service;

import java.text.ParseException;

public interface ICalculateMoney {
	public Long calculatePaymentAmount(Long allRentTime);
	public Long calculateTime(String rentTime) throws ParseException;
}
