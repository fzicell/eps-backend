package hu.icell.eps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.icell.eps.dao.CustomerDAO;
import hu.icell.eps.model.Customer;

@RestController
public class LoginController {
	
	@Autowired
	CustomerDAO customerDAO;

	@SuppressWarnings("finally")
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")    
    public @ResponseBody Customer login(@RequestBody Customer customer) {
		//customerDAO.save(new Customer("Zoltan", "Ferenczik", "zferenczik","valamipass",34));		
		
		try {
			Customer existingCustomer = customerDAO.findByUsername(customer.getUsername());

			if(existingCustomer.getPassword() == customer.getPassword()){
				existingCustomer.setPassword(null);
				return existingCustomer;			
			}			
		} catch (NullPointerException e) {
			return null;
		} finally {
			return null;
		}
    }
    
}
