package com.pay.customer.controller.service;

import java.util.List;


import com.pay.customer.model.Customer;

public interface CustomerService {
	
	List<Customer> getCustomers();
	
	Customer addCustomer(Customer customer);
	
	Customer deleteCus(int cusid);
	
	Customer getCustomerById(int cusid);
}
