package hu.icell.eps;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.icell.eps.dao.ParkingDAO;
import hu.icell.eps.dao.VehicleDAO;
import hu.icell.eps.model.Parking;
import hu.icell.eps.model.Vehicle;

@RestController
public class ParkingController {
	
	@Autowired
	ParkingDAO parkingDAO ;
	
	@Autowired
	VehicleDAO vehicleDAO;
	
    @RequestMapping(value = "/user/{userId}/current_parkings", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Parking> getMyParkings(@PathVariable("userId") Integer customerId, HttpServletResponse response) {

    	List<Parking> myParkings;
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
		try {
//			myParkings = parkingDAO.findByCustomerId(customerId);
			myParkings = parkingDAO.listActiveParkings(customerId);
			
			if(myParkings.equals(null)){
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				return myParkings;
			}
			
			response.setStatus(HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			myParkings = null;
		}

        return myParkings;
        
    }

	@RequestMapping(value = "/user/{userId}/start_parking", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Parking startParking(@PathVariable(value="userId") Integer customerId, @RequestBody Vehicle requestBody, HttpServletResponse response) {

		Parking responseBody = null;
		
		try {
			Vehicle myVehicle = vehicleDAO.findByCustomerIdAndPlateNumber(customerId, requestBody.getPlateNumber());
			
			Parking startedParking = parkingDAO.save(new Parking(myVehicle.getCustomerId(), myVehicle.getVehicleId()));
			
			responseBody = parkingDAO.findOne(startedParking.getParkingId());
			
			if(responseBody.equals(null)){
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				return responseBody;
			}
			
			response.setStatus(HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			responseBody = null;
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		return responseBody;			

    }

    @RequestMapping(value = "/user/{userId}/stop_parking", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody Parking stopParking(@PathVariable(value="userId") Integer customerId, @RequestBody Parking requestBody, HttpServletResponse response) {

		Parking responseBody = null;
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
		try {
			Parking authorized = parkingDAO.findByParkingId(requestBody.getParkingId());
			
			if(!authorized.getCustomerId().equals(customerId))
				return responseBody;
			
			parkingDAO.stopParking(requestBody.getParkingId());			
			responseBody = parkingDAO.findByParkingId(requestBody.getParkingId());

			if(responseBody.equals(null)){
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				return responseBody;
			}
			response.setStatus(HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			responseBody = null;
		}

        return responseBody;
    }
}
    
