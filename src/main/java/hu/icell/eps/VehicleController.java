package hu.icell.eps;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.icell.eps.dao.VehicleDAO;
import hu.icell.eps.model.Vehicle;

@RestController
@RequestMapping("/user/{userId}/vehicles")
public class VehicleController {

	@Autowired
	VehicleDAO vehicleDAO;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Vehicle myVehicles(@PathVariable("userId") Integer customerId, HttpServletResponse response) {
		
		Vehicle myVehicle = new Vehicle();
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);				
		
		try {
			myVehicle = vehicleDAO.findByCustomerId(customerId);
						
			if (myVehicle.equals(null))
				return myVehicle;
			
			response.setStatus(HttpServletResponse.SC_OK);

		} catch (Exception e) {
			myVehicle = null;
		} 
		
		return myVehicle;			
		
    }
    
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Vehicle addVehicle(@RequestBody Vehicle requestedVehicle, @PathVariable("userId") Integer customerId, HttpServletResponse response) {

		Vehicle newVehicle = new Vehicle();
		requestedVehicle.setCustomerId(customerId);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
		try {			
			newVehicle = vehicleDAO.save(requestedVehicle);
			
			if (newVehicle.equals(null))
				return newVehicle;			
			
			response.setStatus(HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			newVehicle = null;
		}
		
		return newVehicle;
    	
    }
}
