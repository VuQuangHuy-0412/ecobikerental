package EcoBikeRental.Dto;

public class RequestResetBalanceDto {
	private String cardCode;
	private String owner;
	private String cvvCode;
	private String dateExpired;
	
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
}
