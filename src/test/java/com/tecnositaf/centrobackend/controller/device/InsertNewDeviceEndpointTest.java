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

import com.tecnositaf.centrobackend.CentroBackendApplication;
import com.tecnositaf.centrobackend.enumeration.Errors;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InsertNewDeviceEndpointTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wepAppContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
	}

	private final String ENDPOINT_RESOURCE_BASE_URL = "/devices";

	/********** REQUEST json **********/
	private final String deviceExampleOneInsertRequestBodyJSON = "{\"id\":null,\"type\":\"Telecamera\",\"description\":\"This is a telecamera test\",\"brand\":\"Sony\",\"isActive\":\"true\",\"inMaintenance\":\"true\",\"lastUpdate\":\"1959-09-10 12:12:12\",\"registrationTime\":\"1959-09-10 12:12:12\",\"weight\":12.5}";
	private final String deviceExampleTwoInsertFailureForEmptyFieldRequestBodyJSON = "{\"id\":23,\"type\":null,\"description\":\"Rossi\",\"brand\":\"Sony\",\"isActive\":\"true\",\"inMaintenance\":\"true\",\"lastUpdate\":\"1959-09-10 12:12:12\",\"registrationTime\":\"1959-09-10 12:12:12\",\"weight\":12.5,\"storageYears\":null}";

	/********** RESPONSE json FAILURE**********/
	private final String insertNewDeviceExampleOneOnInitInvalidFieldResponseJSON = "{\"code\":"
			+ Errors.INVALID_FIELD.code + ", \"errDescription\":\"" + Errors.INVALID_FIELD.description + "\""
			+ ", \"message\":\"Id should be null and Device cannot be null in POST request\"" 
			+ ", \"path\":" + "\"/devices\"" + "}";

	@Test
	public void successOnInit() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_RESOURCE_BASE_URL).contentType(MediaType.APPLICATION_JSON)
				.content(deviceExampleOneInsertRequestBodyJSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				 /********** RESPONSE json **********/
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(0))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("SUCCESS"))
				.andExpect(jsonPath("$.path").exists()).andExpect(jsonPath("$.path").value("http://localhost/devices"))
				 //'devicesInserted'
				.andExpect(jsonPath("$.deviceInserted.type").exists())
				.andExpect(jsonPath("$.deviceInserted.type").value("Telecamera"))
				.andExpect(jsonPath("$.deviceInserted.description").value("This is a telecamera test"))
				.andExpect(jsonPath("$.deviceInserted.brand").exists())
				.andExpect(jsonPath("$.deviceInserted.brand").value("Sony"))
				.andExpect(jsonPath("$.deviceInserted.isActive").exists())
				.andExpect(jsonPath("$.deviceInserted.isActive").value("true"))
				.andExpect(jsonPath("$.deviceInserted.inMaintenance").exists())
				.andExpect(jsonPath("$.deviceInserted.inMaintenance").value("true"))
				.andExpect(jsonPath("$.deviceInserted.lastUpdate").exists())
				.andExpect(jsonPath("$.deviceInserted.lastUpdate").value("1959-09-10 12:12:12"))
				.andExpect(jsonPath("$.deviceInserted.registrationTime").exists())
				.andExpect(jsonPath("$.deviceInserted.registrationTime").value("1959-09-10 12:12:12"))
				.andExpect(jsonPath("$.deviceInserted.weight").exists())
				.andExpect(jsonPath("$.deviceInserted.weight").value(12.5))
				.andExpect(jsonPath("$.deviceInserted.storageYears").exists())
				.andExpect(jsonPath("$.deviceInserted.storageYears").value(61))
				 //'devices'
				.andExpect(jsonPath("$.devices").exists())
				.andExpect(jsonPath("$.devices[0].id").exists())
				.andExpect(jsonPath("$.devices[0].id").value("5e9726448ac3d90cd3ff6243"))
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
				.andExpect(jsonPath("$.devices[1].type").exists())
				.andExpect(jsonPath("$.devices[1].type").value("Telecamera"))
				.andExpect(jsonPath("$.devices[1].description").value("This is a telecamera test"))
				.andExpect(jsonPath("$.devices[1].brand").exists())
				.andExpect(jsonPath("$.devices[1].brand").value("Sony"))
				.andExpect(jsonPath("$.devices[1].isActive").exists())
				.andExpect(jsonPath("$.devices[1].isActive").value("true"))
				.andExpect(jsonPath("$.devices[1].inMaintenance").exists())
				.andExpect(jsonPath("$.devices[1].inMaintenance").value("true"))
				.andExpect(jsonPath("$.devices[1].lastUpdate").exists())
				.andExpect(jsonPath("$.devices[1].lastUpdate").value("1959-09-10 12:12:12"))
				.andExpect(jsonPath("$.devices[1].registrationTime").exists())
				.andExpect(jsonPath("$.devices[1].registrationTime").value("1959-09-10 12:12:12"))
				.andExpect(jsonPath("$.devices[1].weight").exists())
				.andExpect(jsonPath("$.devices[1].weight").value(12.5))
				.andExpect(jsonPath("$.devices[1].storageYears").exists())
				.andExpect(jsonPath("$.devices[1].storageYears").value(61))
				.andExpect(jsonPath("$.devices").isArray()).andExpect(jsonPath("$.devices", hasSize(2)))
				.andExpect(jsonPath("$.size").exists())
				.andExpect(jsonPath("$.size").value(2))

				.andDo(print());
	}

	@Test
	public void failureForNotNullId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_RESOURCE_BASE_URL).contentType(MediaType.APPLICATION_JSON)
				.content(deviceExampleTwoInsertFailureForEmptyFieldRequestBodyJSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				// response
				.andExpect(content().json(insertNewDeviceExampleOneOnInitInvalidFieldResponseJSON)).andDo(print());
	}
}
