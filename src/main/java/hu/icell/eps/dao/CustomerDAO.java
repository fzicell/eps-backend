package hu.icell.eps.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import hu.icell.eps.model.Customer;

@Transactional
public interface CustomerDAO extends CrudRepository<Customer, Integer> {

	public Customer findByCustId(Integer custId);
	public Customer findByUsername(String username);
	public Customer findByUsernameAndPassword(String username, String password);
	
}
