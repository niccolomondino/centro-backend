package com.tecnositaf.centrobackend.controller.device;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tecnositaf.centrobackend.CentroBackendApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetAllDevicesEndpointTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wepAppContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
	}

	private final String ENDPOINT_RESOURCE_BASE_URL = "/users";

	/********** RESPONSE json **********/
	private final String getAllUsersOnInitResponseJSON = "{" + "\"code\":0," + "\"message\":\"SUCCESS\","
			+ "\"devices\":["
			+ "{\"id\":null,\"type\":\"Telecamera\",\"description\":\"This is a telecamera.\",\"brand\":\"Sony\",\"isActive\":\"true\",\"inMaintenance\":\"true\",\"lastUpdate\":\"1959-09-10 12:12:12\",\"registrationTime\":\"1959-09-10 12:12:12\",\"weight\":12.5,\"storageYears\":10}"
			+ "],\"size\":1" + "}";

	@Test
	public void successOnInit() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(0))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.size").exists()).andExpect(jsonPath("$.size").value(1))
				.andExpect(jsonPath("$.devices").exists()).andExpect(jsonPath("$.users").isArray())
				.andExpect(jsonPath("$.devices", hasSize(1)))
				// 'users'
				.andExpect(jsonPath("$.users[0].idUser").exists()).andExpect(jsonPath("$.users[0].idUser").value(12))
				.andExpect(jsonPath("$.users[0].type").exists())
				.andExpect(jsonPath("$.users[0].type").value("Telecamera"))
				.andExpect(jsonPath("$.users[0].description").value("This is a telecamera."))
				.andExpect(jsonPath("$.users[0].brand").exists())
				.andExpect(jsonPath("$.users[0].brand").value("Sony"))
				.andExpect(jsonPath("$.users[0].isActive").exists())
				.andExpect(jsonPath("$.users[0].isActive").value("true"))
				.andExpect(jsonPath("$.users[0].inMaintenance").exists())
				.andExpect(jsonPath("$.users[0].inMaintenance").value("true"))
				.andExpect(jsonPath("$.users[0].lastUpdate").exists())
				.andExpect(jsonPath("$.users[0].lastUpdate").value("1959-09-10 12:12:12"))
				.andExpect(jsonPath("$.users[0].registrationTime").exists())
				.andExpect(jsonPath("$.users[0].registrationTime").value("1959-09-10 12:12:12"))
				.andExpect(jsonPath("$.users[0].weight").exists())
				.andExpect(jsonPath("$.users[0].weight").value(12.5))
				.andExpect(jsonPath("$.users[0].storageYears").exists())
				.andExpect(jsonPath("$.users[0].storageYears").value(10))

				.andDo(print());
	}

	@Test
	public void successOnInitAlternative() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(content().json(getAllUsersOnInitResponseJSON))

				.andDo(print());
	}
}
