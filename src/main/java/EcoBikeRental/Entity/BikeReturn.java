package EcoBikeRental.Entity;

public class BikeReturn {
	private Integer returnId;
	private Integer rentId;
	private String returnTime;
	private Integer dockId;
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

	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public Integer getDockId() {
		return dockId;
	}

	public void setDockId(Integer dockId) {
		this.dockId = dockId;
	}

	public Integer getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}

}
