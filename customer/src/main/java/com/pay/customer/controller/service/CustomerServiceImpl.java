package com.pay.customer.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.customer.model.Customer;
import com.pay.customer.repo.CustomerRespository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRespository customerrespository;
	
	@Override
	public List<Customer> getCustomers() {
		return customerrespository.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) {
		return customerrespository.save(customer);
	}

	@Override
	public Customer deleteCus(int cusid) {
		Customer customer=customerrespository.findOne(cusid);
		return customer;
	}

	@Override
	public Customer getCustomerById(int cusid) {
		return customerrespository.findOne(cusid);
	}

}
