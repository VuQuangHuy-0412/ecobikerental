package EcoBikeRental.Entity;

public class BikeRent {
	private Integer rentId;
	private Integer userId;
	private Integer bikeId;
	private String rentTime;
	private Integer isDeposited;
	private Integer dockId;

	public BikeRent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBikeId() {
		return bikeId;
	}

	public void setBikeId(Integer bikeId) {
		this.bikeId = bikeId;
	}

	public String getRentTime() {
		return rentTime;
	}

	public void setRentTime(String rentTime) {
		this.rentTime = rentTime;
	}

	public Integer getIsDeposited() {
		return isDeposited;
	}

	public void setIsDeposited(Integer isDeposited) {
		this.isDeposited = isDeposited;
	}

	public Integer getDockId() {
		return dockId;
	}

	public void setDockId(Integer dockId) {
		this.dockId = dockId;
	}
}
