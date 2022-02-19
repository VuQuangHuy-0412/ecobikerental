import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import EcoBikeRental.Dao.BikeCategoryDao;
import EcoBikeRental.Entity.BikeCategory;
import EcoBikeRental.Service.RentalFeeCalculator;

/**
 * Unit test calculate money version 1
 *
 */
public class RentalFeeCalculatorTest {

	@Autowired
	BikeCategoryDao bikeCategoryDao;

	RentalFeeCalculator cal = new RentalFeeCalculator();

	// Không quá 10p
	@Test
	void test11() {
		BikeCategory category = bikeCategoryDao.getCategoryById(1);
		assertEquals(cal.calculatePaymentAmount((long) 300, category), 0);
	}

	// Không quá 10p
	@Test
	void test21() {
		BikeCategory category = bikeCategoryDao.getCategoryById(1);
		assertEquals(cal.calculatePaymentAmount((long) 600, category), 0);
	}

	// 30p đầu tiên
	@Test
	void test31() {
		BikeCategory category = bikeCategoryDao.getCategoryById(1);
		assertEquals(cal.calculatePaymentAmount((long) 1500, category), 10000);
	}

	// 30p đầu tiên
	@Test
	void test41() {
		BikeCategory category = bikeCategoryDao.getCategoryById(1);
		assertEquals(cal.calculatePaymentAmount((long) 1800, category), 10000);
	}

	// Trên 30p
	@Test
	void test51() {
		BikeCategory category = bikeCategoryDao.getCategoryById(1);
		assertEquals(cal.calculatePaymentAmount((long) 4200, category), 19000);
	}

	// Không quá 10p
	@Test
	void test12() {
		BikeCategory category = bikeCategoryDao.getCategoryById(2);
		assertEquals(cal.calculatePaymentAmount((long) 300, category), 0);
	}

	// Không quá 10p
	@Test
	void test22() {
		BikeCategory category = bikeCategoryDao.getCategoryById(2);
		assertEquals(cal.calculatePaymentAmount((long) 600, category), 0);
	}

	// 30p đầu tiên
	@Test
	void test32() {
		BikeCategory category = bikeCategoryDao.getCategoryById(2);
		assertEquals(cal.calculatePaymentAmount((long) 1500, category), (long) 1.5*10000);
	}

	// 30p đầu tiên
	@Test
	void test42() {
		BikeCategory category = bikeCategoryDao.getCategoryById(2);
		assertEquals(cal.calculatePaymentAmount((long) 1800, category), (long) 1.5*10000);
	}

	// Trên 30p
	@Test
	void test52() {
		BikeCategory category = bikeCategoryDao.getCategoryById(2);
		assertEquals(cal.calculatePaymentAmount((long) 4200, category), (long) 1.5*19000);
	}

	// Không quá 10p
	@Test
	void test13() {
		BikeCategory category = bikeCategoryDao.getCategoryById(3);
		assertEquals(cal.calculatePaymentAmount((long) 300, category), 0);
	}

	// Không quá 10p
	@Test
	void test23() {
		BikeCategory category = bikeCategoryDao.getCategoryById(3);
		assertEquals(cal.calculatePaymentAmount((long) 600, category), 0);
	}

	// 30p đầu tiên
	@Test
	void test33() {
		BikeCategory category = bikeCategoryDao.getCategoryById(3);
		assertEquals(cal.calculatePaymentAmount((long) 1500, category), (long) 1.5*10000);
	}

	// 30p đầu tiên
	@Test
	void test43() {
		BikeCategory category = bikeCategoryDao.getCategoryById(3);
		assertEquals(cal.calculatePaymentAmount((long) 1800, category), (long) 1.5*10000);
	}

	// Trên 30p
	@Test
	void test53() {
		BikeCategory category = bikeCategoryDao.getCategoryById(3);
		assertEquals(cal.calculatePaymentAmount((long) 4200, category), (long) 1.5*19000);
	}
}
