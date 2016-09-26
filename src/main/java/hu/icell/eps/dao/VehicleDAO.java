package hu.icell.eps.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import hu.icell.eps.model.Vehicle;

@Transactional
public interface VehicleDAO extends CrudRepository<Vehicle, Integer> {

	public Vehicle findByCustomerId(Integer customerId);
	public Vehicle findByPlateNumber(String plateNumber);
	
}
