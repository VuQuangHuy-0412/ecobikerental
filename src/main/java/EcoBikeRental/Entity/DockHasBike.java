package EcoBikeRental.Entity;

public class DockHasBike {
	private Integer id;
	private Integer dockId;
	private Integer categoryId;
	private Integer bikeId;
	private Integer status;
	private String description;

	public DockHasBike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDockId() {
		return dockId;
	}

	public void setDockId(Integer dockId) {
		this.dockId = dockId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getBikeId() {
		return bikeId;
	}

	public void setBikeId(Integer bikeId) {
		this.bikeId = bikeId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
