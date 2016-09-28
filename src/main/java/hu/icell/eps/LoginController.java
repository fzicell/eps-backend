package hu.icell.eps;

import javax.servlet.http.HttpServletResponse;

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

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Customer login(@RequestBody Customer customer, HttpServletResponse response) {

		Customer existingCustomer;
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		try {
			existingCustomer = customerDAO.findByUsername(customer.getUsername());

			if (existingCustomer.getPassword().equals(customer.getPassword())) {
				existingCustomer.setPassword(null);
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				existingCustomer = null;
			}

		} catch (NullPointerException e) {
			existingCustomer = null;
		}

		return existingCustomer;

	}

}
