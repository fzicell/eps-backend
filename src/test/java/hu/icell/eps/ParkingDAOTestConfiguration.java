package hu.icell.eps;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import hu.icell.eps.dao.ParkingDAO;


@Profile("test")
@Configuration
public class ParkingDAOTestConfiguration {

	@Bean
	@Primary
	public ParkingDAO parkingDao() {
		return Mockito.mock(ParkingDAO.class);
	}
}
