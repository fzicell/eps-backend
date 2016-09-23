package hu.icell.eps;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.icell.eps.model.Vehicle;

@RestController
@RequestMapping("/user/{userId}/vehicles")
public class VehicleController {

    
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Vehicle myVehicles(@PathVariable("userId") Integer custId) {
		return null;
		
    }
    
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Vehicle addVehicle(@RequestBody Vehicle vehicle, @PathVariable("userId") Integer custId) {
		return null;
    	
    }
}
