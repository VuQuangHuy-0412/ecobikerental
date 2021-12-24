package EcoBikeRental.Dto;

/**
 * Description: Class Dto contain transaction of body to convert to MD5
 *
 */
public class TransactionToConvertMD5Dto {
	private String command;
	private String cardCode;
	private String owner;
	private String cvvCode;
	private String dateExpired;
	private String transactionContent;
	private Long amount;
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCvvCode() {
		return cvvCode;
	}
	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}
	public String getDateExpired() {
		return dateExpired;
	}
	public void setDateExpired(String dateExpired) {
		this.dateExpired = dateExpired;
	}
	public String getTransactionContent() {
		return transactionContent;
	}
	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
