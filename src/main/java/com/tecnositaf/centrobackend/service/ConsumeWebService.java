package com.tecnositaf.centrobackend.service;

import java.util.List;

import com.tecnositaf.centrobackend.model.Customer;
import com.tecnositaf.centrobackend.response.Response;

public interface ConsumeWebService {

	Response getAreasList();
	
	List<Customer> getCustomersList();

	Customer updateCustomer(Customer customer, String id);

	Response getAlertsList();
}
