package EcoBikeRental.Dto;

public class RequestProcessTransactionDto {
	private String version;
	private RequestTransactionDto transaction;
	private String appCode;
	private String hashCode;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public RequestTransactionDto getTransaction() {
		return transaction;
	}
	public void setTransaction(RequestTransactionDto transaction) {
		this.transaction = transaction;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
}
