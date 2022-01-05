package EcoBikeRental.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * Calculate money varsion 1
 *
 */
@Service
public class CalculateMoney implements ICalculateMoney{
	
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
	
	/**
	 * Description: method calculate the time of a rent transaction
	 * @param rentTime: the start rent time "2021-12-25 21:38:00"
	 * @return Long: the time from start to end rent bike (seconds)
	 * @throws ParseException
	 */
	public Long calculateTime(String rentTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date dateRent = formatter.parse(rentTime);
		
		Calendar c = Calendar.getInstance();
		c.setTime(dateRent);
		long rentTimestamp = c.getTimeInMillis();
		long returnTimestamp = System.currentTimeMillis();
		
		long allRentTime = (returnTimestamp - rentTimestamp)/1000;
		return allRentTime;
	}
}
