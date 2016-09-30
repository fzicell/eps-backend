package hu.icell.eps;

import java.nio.charset.Charset;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import hu.icell.eps.dao.CustomerDAO;
import hu.icell.eps.dao.VehicleDAO;
import hu.icell.eps.model.Customer;
import hu.icell.eps.model.Vehicle;

@WebAppConfiguration
@ContextConfiguration(classes = { CustomerDAOTestConfiguration.class, VehicleDAOTestConfiguration.class,
		ParkingDAOTestConfiguration.class, ParkingController.class })
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
public class ParkingControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private VehicleDAO vehicleDao;

	private Customer customer = new Customer("Lajos", "Szkajvolker", "szkaj", "jelszo", 18);
	
	private Vehicle vehicle = new Vehicle(1, "KSH-112");

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

}
