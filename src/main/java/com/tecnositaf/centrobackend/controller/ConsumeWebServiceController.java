package com.tecnositaf.centrobackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnositaf.centrobackend.model.Customer;
import com.tecnositaf.centrobackend.response.Response;
import com.tecnositaf.centrobackend.service.ConsumeWebService;

@RestController
@RequestMapping("/consume")
public class ConsumeWebServiceController {

	@Autowired
	ConsumeWebService consumeWebService;

	/**
	 * Test function Get area list from example web service
	 * 
	 * @return
	 */
	@GetMapping("/alerts")
	public ResponseEntity<Response> getAlerts() {

		return ResponseEntity.status(HttpStatus.OK).body(consumeWebService.getAlertsList());

	}

	/**
	 * Test function Get area list from example web service
	 * 
	 * @return
	 */
	@GetMapping(value = "/areas")
	public ResponseEntity<Response> getAreasList() {

		return ResponseEntity.status(HttpStatus.OK).body(consumeWebService.getAreasList());

	}

	/**
	 * Test function Get customer list from example web service
	 * 
	 * @return
	 */
	@GetMapping(value = "/customers")
	public ResponseEntity<List<Customer>> getCustomersList() {

		return ResponseEntity.status(HttpStatus.OK).body(consumeWebService.getCustomersList());
	}

	/**
	 * Test function Update customer from example web service
	 * 
	 * @return
	 */
	@PutMapping(value = "/customers/{id}")
	public ResponseEntity<Customer> updateProduct(@PathVariable("id") String id, @RequestBody Customer customer) {

		return ResponseEntity.status(HttpStatus.OK).body(consumeWebService.updateCustomer(customer, id));

	}

}
