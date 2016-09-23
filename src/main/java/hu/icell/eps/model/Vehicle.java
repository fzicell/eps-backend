package hu.icell.eps.model;

public class Vehicle {

    private Integer vehicleId;
    private Integer customerId;
	private String plateNumber;

	public Vehicle(){
		
	}
		
	public Vehicle(int vehicleId, int customerId, String plateNumber) {
    	setVehicleId(vehicleId);
    	setCustomerId(customerId);
    	setPlateNumber(plateNumber);
	}
    
    public Vehicle(int customerId, String plateNumber) {
    	setCustomerId(customerId);
    	setPlateNumber(plateNumber);
	}

	public Integer getVehicleId() {
		return vehicleId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

}
