package hu.icell.eps;


import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import hu.icell.eps.dao.VehicleDAO;

@Profile("test")
@Configuration
public class VehicleDAOTestConfiguration {

	@Bean
	@Primary
	public VehicleDAO vehicleDao() {
		return Mockito.mock(VehicleDAO.class);
	}
}