package com.pay.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pay.customer.model.Customer;

@Repository
public interface CustomerRespository extends JpaRepository<Customer,Integer>{

}
