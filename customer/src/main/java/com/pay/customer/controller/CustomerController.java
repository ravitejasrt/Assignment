package com.pay.customer.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.customer.controller.service.CustomerService;
import com.pay.customer.exception.CustomerServiceException;
//import com.pay.customer.exception.EntityNotFoundException;
import com.pay.customer.exception.RecordNotFoundException;
import com.pay.customer.exception.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import com.pay.customer.model.Customer;
import com.pay.customer.repo.CustomerRespository;

@RestController
@RequestMapping("/cst")
public class CustomerController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerService customerservice;

	@GetMapping("/customers")
	public ResponseEntity<?> getCustomers() throws ResourceNotFoundException, CustomerServiceException{
	   LOGGER.info("Details of all customers");
	   List<Customer> lstcust=customerservice.getCustomers();
	   if(lstcust==null){
		throw new ResourceNotFoundException("Employee not found");	
	   }
	   return new ResponseEntity<>(lstcust, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer)
	{
		LOGGER.info("Details of a customer "+customer);
		return new ResponseEntity<Customer>(customerservice.addCustomer(customer), HttpStatus.OK);
	}
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCus(@PathVariable(value = "id") Integer cusid) {
		LOGGER.info("Delete customer");
		Customer cust = customerservice.getCustomerById(cusid);
		LOGGER.info("Details of customer "+cust);
		if(cust!=null){
		  Customer cus=customerservice.deleteCus(cusid);	
		  LOGGER.info("customer deleted");
		}
		else{
			LOGGER.error("Entity not found");	
//	        throw new com.pay.customer.exception.EntityNotFoundException(Customer.class, "id",""+cusid);	
//			throw new EntityNotFoundException("Entity Not Found Exception");	
			throw new EntityNotFoundException();		
		}
	  //  return ResponseEntity.ok().build();
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
	
	@GetMapping(value = "/customer/{id}") 
	public ResponseEntity<Customer> getCustomerById (@PathVariable("id") int cusid)
	{
		LOGGER.info("Customer id "+cusid);	
		Customer customer = customerservice.getCustomerById(cusid);
		LOGGER.info("Details of all customers "+customer);
	    if(customer == null)
	    {
	 	   LOGGER.info("Customer Not Exists");
           throw new com.pay.customer.exception.EntityNotFoundException(Customer.class, "id",""+cusid);
	//		throw new EntityNotFoundException("Entity Not Found Exception");
	//		throw new EntityNotFoundException();			 	   
	    }
	    return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	
	
}
