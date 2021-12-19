package EcoBikeRental.Entity;

public class PaymentTransaction {
	private Integer paymentTransactionId;
	private Integer rentId;
	private Integer time; //seconds
	private Long payment;
	private Integer depositTransactionId;
	private Long returnedMoney;
	private String createdTime;

	public PaymentTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPaymentTransactionId() {
		return paymentTransactionId;
	}

	public void setPaymentTransactionId(Integer paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}

	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Long getPayment() {
		return payment;
	}

	public void setPayment(Long payment) {
		this.payment = payment;
	}

	public Integer getDepositTransactionId() {
		return depositTransactionId;
	}

	public void setDepositTransactionId(Integer depositTransactionId) {
		this.depositTransactionId = depositTransactionId;
	}

	public Long getReturnedMoney() {
		return returnedMoney;
	}

	public void setReturnedMoney(Long returnedMoney) {
		this.returnedMoney = returnedMoney;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
}
