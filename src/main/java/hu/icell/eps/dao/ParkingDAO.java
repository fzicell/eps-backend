package hu.icell.eps.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import hu.icell.eps.model.Parking;

@Transactional
public interface ParkingDAO extends CrudRepository<Parking, Integer> {
	
	public Parking findByParkingId(Integer parkingId);
	public Parking findByCustomerIdAndVehicleId(Integer customerId, Integer vehicleId);
	
}
