package hu.icell.eps;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/{userId}/vehicles")
public class VehicleController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
    public Vehicle myVehicles() {
        return new Vehicle(counter.incrementAndGet(), "myVehicles");
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Vehicle addVehicle() {
        return new Vehicle(counter.incrementAndGet(), "addVehicle");
    }
}
