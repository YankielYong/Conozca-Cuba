package dto;

public class VehicleDTO {

	private int vehicleCode;
	private String vehiclePlate;
	private String vehicleBrand;
	private int yearOfProduction;
	private int capacityWithoutLuggage;
	private int capacityWithLuggage;
	private int totalCapacity;
	
	public VehicleDTO(int vehicleCode, String vehiclePlate, String vehicleBrand, int yearOfProduction,
			int capacityWithoutLuggage, int capacityWithLuggage, int totalCapacity) {
		this.vehicleCode = vehicleCode;
		this.vehiclePlate = vehiclePlate;
		this.vehicleBrand = vehicleBrand;
		this.yearOfProduction = yearOfProduction;
		this.capacityWithoutLuggage = capacityWithoutLuggage;
		this.capacityWithLuggage = capacityWithLuggage;
		this.totalCapacity = totalCapacity;
	}

	public int getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(int vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public int getCapacityWithoutLuggage() {
		return capacityWithoutLuggage;
	}

	public void setCapacityWithoutLuggage(int capacityWithoutLuggage) {
		this.capacityWithoutLuggage = capacityWithoutLuggage;
	}

	public int getCapacityWithLuggage() {
		return capacityWithLuggage;
	}

	public void setCapacityWithLuggage(int capacityWithLuggage) {
		this.capacityWithLuggage = capacityWithLuggage;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
}
