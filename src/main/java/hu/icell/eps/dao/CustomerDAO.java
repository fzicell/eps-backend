package hu.icell.eps.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import hu.icell.eps.model.Customer;

@Transactional
public interface CustomerDAO extends CrudRepository<Customer, Integer> {

	public Customer findByCustomerId(Integer customerId);
	public Customer findByUsername(String username);
	
}
