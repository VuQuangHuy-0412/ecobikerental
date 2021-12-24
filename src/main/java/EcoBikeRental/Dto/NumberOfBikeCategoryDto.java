package EcoBikeRental.Dto;

import EcoBikeRental.Entity.BikeCategory;

/**
 * Description: Class Dto contain info number of category of a dock
 *
 */
public class NumberOfBikeCategoryDto {
	private BikeCategory bikeCategory;
	private Integer numbers;
	
	public BikeCategory getBikeCategory() {
		return bikeCategory;
	}
	public void setBikeCategory(BikeCategory bikeCategory) {
		this.bikeCategory = bikeCategory;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
}
