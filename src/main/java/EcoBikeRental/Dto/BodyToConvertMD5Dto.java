package EcoBikeRental.Dto;

public class BodyToConvertMD5Dto {
	private String secretKey;
	private TransactionToConvertMD5Dto transaction;
	
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public TransactionToConvertMD5Dto getTransaction() {
		return transaction;
	}
	public void setTransaction(TransactionToConvertMD5Dto transaction) {
		this.transaction = transaction;
	}
}
