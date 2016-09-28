package hu.icell.eps.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "parkingorder")
public class Parking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer parkingId;

	@NotNull
	private Integer customerId;

	@NotNull
	private Integer vehicleId;

	@LastModifiedDate
	private Timestamp updatedAt;

	@CreatedDate
	private Timestamp startedAt;

	private Timestamp finishedAt;

	public Parking() {
	}

	public Parking(Integer customerId, Integer vehicleId) {
		this.customerId = customerId;
		this.vehicleId = vehicleId;
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Timestamp startedAt) {
		this.startedAt = startedAt;
	}

	public Timestamp getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(Timestamp finishedAt) {
		this.finishedAt = finishedAt;
	}

}
