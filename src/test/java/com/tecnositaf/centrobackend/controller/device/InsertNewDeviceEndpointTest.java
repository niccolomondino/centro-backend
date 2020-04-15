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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tecnositaf.centrobackend.CentroBackendApplication;
import com.tecnositaf.centrobackend.enumeration.Errors;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDateTime;

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
   

    /**********     REQUEST json    **********/
    private final String deviceExampleOneInsertRequestBodyJSON = "{\"id\":null,\"type\":\"Telecamera\",\"description\":\"This is a telecamera test.\",\"brand\":\"Sony\",\"isActive\":\"true\",\"inMaintenance\":\"true\",\"lastUpdate\":\"1959-09-10 12:12:12\",\"registrationTime\":\"1959-09-10 12:12:12\",\"weight\":12.5,\"storageYears\":null}";
    private final String deviceExampleTwoInsertFailureForEmptyFieldRequestBodyJSON = "{\"id\":null,\"type\":null,\"description\":\"Rossi\",\"brand\":\"Sony\",\"isActive\":\"true\",\"inMaintenance\":\"true\",\"lastUpdate\":\"1959-09-10 12:12:12\",\"registrationTime\":\"1959-09-10 12:12:12\",\"weight\":12.5,\"storageYears\":null}";

    /**********     RESPONSE json    **********/
    private final String insertNewDeviceExampleOneOnInitResponseJSON = "{" +
            "\"code\":0," +
            "\"message\":\"SUCCESS\"," +
            "\"userInserted\": {\"id\":null,\"type\":\"Mario\",\"description\":\"Rossi\",\"brand\":\"Sony\",\"isActive\":\"true\",\"inMaintenance\":\"true\",\"lastUpdate\":\"1959-09-10 12:12:12\",\"registrationTime\":\"1959-09-10 12:12:12\",\"weight\":12.5,\"storageYears\":null}," +
            "\"users\":[" +
                "{\"id\":12,\"type\":\"Mario\",\"description\":\"Rossi\",\"brand\":\"Sony\",\"isActive\":\"true\",\"inMaintenance\":\"true\",\"lastUpdate\":\"1959-09-10 12:12:12\",\"registrationTime\":\"1959-09-10 12:12:12\",\"weight\":12.5,\"storageYears\":null}" + ", " +
                "{\"id\":13,\"type\":\"Mario\",\"description\":\"Rossi\",\"brand\":\"Sony\",\"isActive\":\"true\",\"inMaintenance\":\"true\",\"lastUpdate\":\"1959-09-10 12:12:12\",\"registrationTime\":\"1959-09-10 12:12:12\",\"weight\":12.5,\"storageYears\":null}" +
            "]," +
            "\"size\":2" +
        "}";
    private final String insertNewDeviceExampleOneOnInitInvalidFieldResponseJSON = "{\"code\":"+ Errors.INVALID_FIELD.code +", \"message\":\""+ Errors.INVALID_FIELD.description +"\"}";



    @Test
    public void successOnInit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_RESOURCE_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content( deviceExampleOneInsertRequestBodyJSON )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //response
                .andExpect(content().json( insertNewDeviceExampleOneOnInitResponseJSON ))
                .andDo(print());
    }

    @Test
    public void failureForEmptyField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_RESOURCE_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content( deviceExampleTwoInsertFailureForEmptyFieldRequestBodyJSON )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //response
                .andExpect(content().json( insertNewDeviceExampleOneOnInitInvalidFieldResponseJSON ))
                .andDo(print());
    }
}
