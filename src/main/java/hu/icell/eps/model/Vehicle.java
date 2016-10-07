package hu.icell.eps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "vehicle")
@JsonInclude(Include.NON_NULL)
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vehicleId;

	@NotNull
	//@JsonProperty(value = "userId")
	private Integer customerId;

	@NotNull
	private String plateNumber;

	public Vehicle() {

	}

	public Vehicle(int customerId, String plateNumber) {
		this.customerId = customerId;
		this.plateNumber = plateNumber;
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
