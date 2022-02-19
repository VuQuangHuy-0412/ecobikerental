package EcoBikeRental.Entity;

public class BikeReturn {
	private Integer returnId;
	private BikeRent rent;
	private String returnTime;
	private Dock dock;
	private Integer isPaid;

	public BikeReturn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getReturnId() {
		return returnId;
	}

	public void setReturnId(Integer returnId) {
		this.returnId = returnId;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public Integer getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}

	public BikeRent getRent() {
		return rent;
	}

	public void setRent(BikeRent rent) {
		this.rent = rent;
	}

	public Dock getDock() {
		return dock;
	}

	public void setDock(Dock dock) {
		this.dock = dock;
	}

}
