import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import EcoBikeRental.Service.CalculateMoney;

/**
 * Unit test calculate money version 1
 *
 */
public class CalculateMoneyTest {
	
	CalculateMoney cal = new CalculateMoney();
	
	@Test
	void test1() {
		assertEquals(cal.calculatePaymentAmount((long) 300), 0);
	}
	
	@Test
	void test2() {
		assertEquals(cal.calculatePaymentAmount((long) 600), 0);
	}
	
	@Test
	void test3() {
		assertEquals(cal.calculatePaymentAmount((long) 1500), 10000);
	}
	
	@Test
	void test4() {
		assertEquals(cal.calculatePaymentAmount((long) 1800), 10000);
	}
	
	@Test
	void test5() {
		assertEquals(cal.calculatePaymentAmount((long) 4200), 19000);
	}
}
