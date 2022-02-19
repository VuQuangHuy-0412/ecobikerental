package EcoBikeRental.Entity;

public class PaymentTransaction {
	private Integer paymentTransactionId;
	private BikeRent rent;
	private Long time; //seconds
	private Long payment;
	private DepositTransaction depositTransaction;
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

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getPayment() {
		return payment;
	}

	public void setPayment(Long payment) {
		this.payment = payment;
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

	public BikeRent getRent() {
		return rent;
	}

	public void setRent(BikeRent rent) {
		this.rent = rent;
	}

	public DepositTransaction getDepositTransaction() {
		return depositTransaction;
	}

	public void setDepositTransaction(DepositTransaction depositTransaction) {
		this.depositTransaction = depositTransaction;
	}
}
