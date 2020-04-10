package com.tecnositaf.centrobackend.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tecnositaf.centrobackend.model.Customer;
import com.tecnositaf.centrobackend.response.GetAreasResponse;
import com.tecnositaf.centrobackend.response.GetAlertsResponse;
import com.tecnositaf.centrobackend.response.Response;
import com.tecnositaf.centrobackend.utilities.CommonsUtility;

@Service
public class ConsumeWebServiceImpl implements ConsumeWebService {

	@Autowired
	RestTemplate restTemplate;
	
	/*
	 * Get Alerts in custom response via Rest Template 
	 */
	public Response getAlertsList() {

		return restTemplate.getForObject("http://localhost:8090/api/alerts", 
										  GetAlertsResponse.class);
	}
	

	/*
	 * Rest Template method using custom response
	 */
	public Response getAreasList() {

		return restTemplate.getForObject("http://api.football-data.org/v2/areas", GetAreasResponse.class);
		
	}

	/*
	 * TOFIX: alternative method to get list from web service
	 * Rest Template method using List as return
	 */
	public List<Customer> getCustomersList() {
	 /*
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

 		String responseJson = restTemplate
 			.exchange("http://localhost:8090/api/customers", HttpMethod.GET, entity, String.class).getBody();

		return Commons.fromJsonToCollection(responseJson, Customer.class, List.class);
		*/
		return null;
	}
	

	public Customer updateCustomer(Customer customer, String id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);

		String responseJson = restTemplate
				.exchange("http://localhost:8080/api/customers/" + id, HttpMethod.PUT, entity, String.class).getBody();
		
	    return CommonsUtility.fromJsonToObject(responseJson, Customer.class);
		
	}
	
	
	

}
