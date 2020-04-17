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

	private final String ENDPOINT_RESOURCE_BASE_URL = "/devices";

	/********** RESPONSE json **********/
	private final String getAllDevicesOnInitResponseJSON = "{" + "\"code\":0," 
			+ "\"message\":\"SUCCESS\","
			+ "\"path\":\"http://localhost/devices\","
			+ "\"devices\":["
			+ "{\"id\":\"5e9726448ac3d90cd3ff6243\",\"type\":\"Telecamera\",\"description\":\"This is a telecamera\",\"brand\":\"Sony\",\"isActive\":true,\"inMaintenance\":false,\"weight\":30.5,\"lastUpdate\":\"2019-08-01 09:30:00\",\"registrationTime\":\"2019-08-01 09:30:00\",\"storageYears\":1}"
			+ "],\"size\":1" + "}";

	@Test
	public void successOnInit() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(0))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.devices").exists()).andExpect(jsonPath("$.devices").isArray())
				.andExpect(jsonPath("$.devices", hasSize(1)))
				.andExpect(jsonPath("$.size").exists())
				.andExpect(jsonPath("$.size").value(1))
				// 'devices'
				.andExpect(jsonPath("$.devices[0].id").exists()).andExpect(jsonPath("$.devices[0].id").value("5e9726448ac3d90cd3ff6243"))
				.andExpect(jsonPath("$.devices[0].type").exists())
				.andExpect(jsonPath("$.devices[0].type").value("Telecamera"))
				.andExpect(jsonPath("$.devices[0].description").value("This is a telecamera"))
				.andExpect(jsonPath("$.devices[0].brand").exists())
				.andExpect(jsonPath("$.devices[0].brand").value("Sony"))
				.andExpect(jsonPath("$.devices[0].isActive").exists())
				.andExpect(jsonPath("$.devices[0].isActive").value("true"))
				.andExpect(jsonPath("$.devices[0].inMaintenance").exists())
				.andExpect(jsonPath("$.devices[0].inMaintenance").value("false"))
				.andExpect(jsonPath("$.devices[0].lastUpdate").exists())
				.andExpect(jsonPath("$.devices[0].lastUpdate").value("2019-08-01 09:30:00"))
				.andExpect(jsonPath("$.devices[0].registrationTime").exists())
				.andExpect(jsonPath("$.devices[0].registrationTime").value("2019-08-01 09:30:00"))
				.andExpect(jsonPath("$.devices[0].weight").exists())
				.andExpect(jsonPath("$.devices[0].weight").value(30.5))
				.andExpect(jsonPath("$.devices[0].storageYears").exists())
				.andExpect(jsonPath("$.devices[0].storageYears").value(1))

				.andDo(print());
	}

	@Test
	public void successOnInitAlternative() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(content().json(getAllDevicesOnInitResponseJSON))

				.andDo(print());
	}
}
