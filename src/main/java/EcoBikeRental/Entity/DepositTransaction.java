package EcoBikeRental.Entity;

public class DepositTransaction {
	private Integer depositTransactionId;
	private BikeRent rent;
	private Long moneyAmount;
	private String createdAt;
	private String description;
	private String status;
	private String depositBy;

	public DepositTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getDepositTransactionId() {
		return depositTransactionId;
	}

	public void setDepositTransactionId(Integer depositTransactionId) {
		this.depositTransactionId = depositTransactionId;
	}

	public BikeRent getRent() {
		return rent;
	}

	public void setRent(BikeRent rent) {
		this.rent = rent;
	}

	public Long getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(Long moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepositBy() {
		return depositBy;
	}

	public void setDepositBy(String depositBy) {
		this.depositBy = depositBy;
	}
}
