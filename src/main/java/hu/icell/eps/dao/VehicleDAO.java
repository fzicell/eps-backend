package hu.icell.eps.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import hu.icell.eps.model.Vehicle;

@Transactional
public interface VehicleDAO extends CrudRepository<Vehicle, Integer> {

	public List<Vehicle> findByCustomerId(Integer customerId);
	public List<Vehicle> findByPlateNumber(String plateNumber);
	public Vehicle findByCustomerIdAndPlateNumber(Integer customerId, String plateNumber);
	
}
