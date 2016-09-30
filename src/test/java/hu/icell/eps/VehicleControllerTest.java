package hu.icell.eps;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import hu.icell.eps.dao.CustomerDAO;
import hu.icell.eps.dao.VehicleDAO;
import hu.icell.eps.model.Customer;
import hu.icell.eps.model.Vehicle;

@WebAppConfiguration
@ContextConfiguration(classes = { CustomerDAOTestConfiguration.class, VehicleDAOTestConfiguration.class,
		VehicleController.class })
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private VehicleDAO vehicleDao;

	private Customer customer = new Customer("Lajos", "Szkajvolker", "szkaj", "jelszo", 18);

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void requestListWithoutCustomerId() throws Exception, Exception {

	}

	@Test
	public void requestVehicleWithEmptyCustomers() throws Exception {
		Mockito.when(vehicleDao.findByCustomerId(1)).thenReturn(null);
		mockMvc.perform(get("/user/1/vehicles")).andExpect(status().isForbidden());

	}

	@Test
	public void requestListWithValidCustomerId() throws Exception {
		// returns one vehicle associated with customer id
		List<Vehicle> vehicle = new ArrayList<Vehicle>();
		vehicle.add(new Vehicle(1, "ABC-442"));
		Mockito.when(vehicleDao.findByCustomerId(1)).thenReturn(vehicle);
		mockMvc.perform(get("/user/1/vehicles")).andExpect(status().isOk());

	}

	@Test
	public void requestVehicleWithInvalidCustomerId() throws Exception {
		List<Vehicle> vehicle = new ArrayList<Vehicle>();
		vehicle.add(new Vehicle(1, "ABC-442"));
		Mockito.when(vehicleDao.findByCustomerId(2)).thenReturn(null);
		mockMvc.perform(get("/user/2/vehicles")).andExpect(status().isForbidden());

	}

	@Test
	public void addVehicle() throws Exception, Exception {
		Vehicle vehicle = new Vehicle(1, "ABC-442");
		vehicle.setVehicleId(1);
		
		Mockito.when(vehicleDao.save(any(Vehicle.class))).thenReturn(vehicle);
		
		mockMvc.perform(post("/user/1/vehicles").contentType(contentType).content(json(vehicle)))
				.andExpect(status().isOk());
		
		Mockito.verify(vehicleDao).save(any(Vehicle.class));
	}

	 @Test
	public void addEmptyVehicle() throws Exception, Exception {
		Vehicle vehicleASD = new Vehicle();
		Mockito.when(vehicleDao.save(any(Vehicle.class))).thenReturn(null);
		mockMvc.perform(post("/user/1/vehicles").contentType(contentType).content(json(vehicleASD)))
				.andExpect(status().isForbidden());
	}

	

	protected String json(Object o) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.writeValueAsString(o);
	}

}
