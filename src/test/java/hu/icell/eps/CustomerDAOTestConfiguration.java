package hu.icell.eps;


import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import hu.icell.eps.dao.CustomerDAO;

@Profile("test")
@Configuration
public class CustomerDAOTestConfiguration {

	@Bean
	@Primary
	public CustomerDAO customerDao() {
		return Mockito.mock(CustomerDAO.class);
	}
}
