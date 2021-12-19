package EcoBikeRental.Entity;

public class Dock {
	private Integer dockId;
	private String name;
	private String address;
	private String description;
	private String createdAt;
	private String updatedAt;

	public Dock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getDockId() {
		return dockId;
	}

	public void setDockId(Integer dockId) {
		this.dockId = dockId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
