import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import EcoBikeRental.Service.RentalFeeCalculator;

/**
 * Unit test calculate money version 1
 *
 */
public class RentalFeeCalculatorTest {
	
	RentalFeeCalculator cal = new RentalFeeCalculator();
	
	//Không quá 10p
	@Test
	void test1() {
		assertEquals(cal.calculatePaymentAmount((long) 300), 0);
	}

	//Không quá 10p
	@Test
	void test2() {
		assertEquals(cal.calculatePaymentAmount((long) 600), 0);
	}
	
	//30p đầu tiên
	@Test
	void test3() {
		assertEquals(cal.calculatePaymentAmount((long) 1500), 10000);
	}
	
	//30p đầu tiên
	@Test
	void test4() {
		assertEquals(cal.calculatePaymentAmount((long) 1800), 10000);
	}
	
	//Trên 30p
	@Test
	void test5() {
		assertEquals(cal.calculatePaymentAmount((long) 4200), 19000);
	}
}
