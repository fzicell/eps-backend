package hu.icell.eps;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import hu.icell.eps.dao.ParkingDAO;
import hu.icell.eps.dao.VehicleDAO;
import hu.icell.eps.model.Customer;
import hu.icell.eps.model.Parking;
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

	@Autowired
	private ParkingDAO parkingDao;

	private Customer customer = new Customer("Lajos", "Szkajvolker", "szkaj", "jelszo", 18);

	private Vehicle vehicle = new Vehicle(1, "KSH-112");

	private Parking parking;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void requestParkingWithoutActiveParkingTest() throws Exception {

		Mockito.when(parkingDao.listActiveParkings(1)).thenReturn(null);
		mockMvc.perform(get("/user/1/current_parkings")).andExpect(status().isNoContent());
	}

	@Test
	public void requestParkingWithActiveParkingTest() throws Exception {
		parking = new Parking(1, 1);
		parking.setParkingId(1);
		List<Parking> parkingList = new ArrayList<Parking>();
		parkingList.add(parking);

		Mockito.when(parkingDao.listActiveParkings(1)).thenReturn(parkingList);
		mockMvc.perform(get("/user/1/current_parkings")).andExpect(status().isOk());

	}

	@Test
	public void startParkingTest() throws Exception, Exception {
		parking = new Parking(1, 1);
		parking.setParkingId(1);
		Date date = new Date();
		parking.setStartedAt(new Timestamp(date.getTime()));
		vehicle.setVehicleId(1);

		// Mockito.when(vehicleDao.findByCustomerIdAndPlateNumber(any(Customer.class).getCustomerId(),
		// any(Vehicle.class).getPlateNumber()))
		// .thenReturn(vehicle);

		Mockito.when(vehicleDao.findByCustomerIdAndPlateNumber(1, "KSH-112")).thenReturn(vehicle);

		Mockito.when(parkingDao.save(any(Parking.class))).thenReturn(parking);

		// Mockito.when(parkingDao.findOne(any(Parking.class).getParkingId())).thenReturn(parking);
		Mockito.when(parkingDao.findOne(1)).thenReturn(parking);

		mockMvc.perform(post("/user/1/start_parking").contentType(contentType).content(json(vehicle)))
				.andExpect(status().isOk());
	}

	@Test
	public void startParkingWithFailureTest() throws Exception, Exception {
		parking = new Parking(1, 1);
		parking.setParkingId(1);
		Date date = new Date();
		parking.setStartedAt(new Timestamp(date.getTime()));
		vehicle.setVehicleId(1);
		Mockito.when(vehicleDao.findByCustomerIdAndPlateNumber(1, "KSH-112")).thenReturn(vehicle);
		Mockito.when(parkingDao.save(any(Parking.class))).thenReturn(parking);
		Mockito.when(parkingDao.findOne(1)).thenReturn(null);

		mockMvc.perform(post("/user/1/start_parking").contentType(contentType).content(json(vehicle)))
				.andExpect(status().isNoContent());
	}

	@Test
	public void stopParkingTest() throws Exception, Exception {
		parking = new Parking(1, 1);
		parking.setParkingId(1);
		Date date = new Date();
		parking.setStartedAt(new Timestamp(date.getTime()));
		vehicle.setVehicleId(1);
		Mockito.when(parkingDao.findByParkingId(1)).thenReturn(parking);

		Mockito.doNothing().when(parkingDao).stopParking(1);

		mockMvc.perform(post("/user/1/stop_parking").contentType(contentType).content(json(parking)))
				.andExpect(status().isOk());

	}
	
	@Test
	public void stopParkingWithFailureTest() throws Exception, Exception {
		parking = new Parking(1, 1);
		parking.setParkingId(1);
		Date date = new Date();
		parking.setStartedAt(new Timestamp(date.getTime()));
		vehicle.setVehicleId(1);
		Mockito.when(parkingDao.findByParkingId(1)).thenReturn(null);

		Mockito.doNothing().when(parkingDao).stopParking(1);

		mockMvc.perform(post("/user/1/stop_parking").contentType(contentType).content(json(parking)))
				.andExpect(status().isForbidden());

	}

	protected String json(Object o) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.writeValueAsString(o);
	}
}
