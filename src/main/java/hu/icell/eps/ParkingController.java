package hu.icell.eps;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.icell.eps.dao.ParkingDAO;
import hu.icell.eps.model.Parking;

@RestController
public class ParkingController {

	@Autowired
	ParkingDAO parkingDAO ;

    @RequestMapping(value = "/user/{userId}/current_parkings", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
    public @ResponseBody Parking getMyParkings(@PathVariable("userId") Integer customerId, @RequestBody Parking requestBody, HttpServletResponse response) {

    	Parking myParkings = new Parking();
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
		try {
			myParkings = parkingDAO.findByCustomerIdAndVehicleId(customerId, requestBody.getVehicleId());
			
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
    public Parking startParking(@PathVariable(value="userId") Integer customerId, @RequestBody Parking requestBody, HttpServletResponse response) {

		Parking responseBody = new Parking();
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
		try {
			requestBody.setCustomerId(customerId);
			responseBody = parkingDAO.save(requestBody);
			
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

    @RequestMapping(value = "/user/{userId}/stop_parking", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Parking stopParking(@PathVariable(value="userId") Integer customerId, @RequestBody Parking requestBody, HttpServletResponse response) {

		Parking responseBody = new Parking();
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
		try {
			Parking startedParking = parkingDAO.findByParkingId(requestBody.getParkingId());
			startedParking.setFinishedAt(new Timestamp(System.currentTimeMillis()));
			responseBody = parkingDAO.save(startedParking);
			
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
    
