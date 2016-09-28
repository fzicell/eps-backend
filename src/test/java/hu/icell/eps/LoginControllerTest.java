/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hu.icell.eps;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;

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

@WebAppConfiguration
@ContextConfiguration(classes = { CustomerDAOTestConfiguration.class, VehicleDAOTestConfiguration.class,
		LoginController.class })
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private VehicleDAO vehicleDao;

	private Customer customer = new Customer("Lajos", "Szkajvolker", "szkaj", "jelszo", 18);

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	/*@Test
	public void expectResponseStringTest() throws Exception {
		mockMvc.perform(get("/login2")).andExpect(status().isOk()).andExpect(content().string("lajos"));

	}*/

	@Test
	public void bodyWithInvalidPayload() throws Exception {

		mockMvc.perform(post("/login/").contentType(contentType).content(json(customer)))
				.andExpect(status().isForbidden());
	}

	@Test
	public void bodyWithValidPayloadButInvalidPassword() throws Exception {
		// Expecting NoContent cause invalid password
		Mockito.when(customerDao.findByUsername("szkaj"))
				.thenReturn(new Customer("Lajos", "Szkajvolker", "szkaj", "jelszo12", 18));

		mockMvc.perform(post("/login/").contentType(contentType).content(json(customer)))
				.andExpect(status().isForbidden());
	}

	@Test
	public void bodyWithValidPayload() throws Exception {
		// Expecting customer with same parameters as posted
		Mockito.when(customerDao.findByUsername("szkaj")).thenReturn(customer);

		mockMvc.perform(post("/login/").contentType(contentType).content(json(customer))).andExpect(status().isOk());
	}

	protected String json(Object o) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.writeValueAsString(o);
	}

}