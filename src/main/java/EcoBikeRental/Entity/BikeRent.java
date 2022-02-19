package EcoBikeRental.Entity;

public class BikeRent {
	private Integer rentId;
	private Integer userId;
	private Bike bike;
	private String rentTime;
	private Integer isDeposited;
	private Dock dock;

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

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public Dock getDock() {
		return dock;
	}

	public void setDock(Dock dock) {
		this.dock = dock;
	}
}
