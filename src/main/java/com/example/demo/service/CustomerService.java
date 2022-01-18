package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerDao;

@Service
public class CustomerService {
	@Autowired
	CustomerDao custDao;

	public Optional<Customer> getCustomerById(int id) {
		Optional<Customer> cust = custDao.findById(id);
		//return cust.isPresent()?cust.get():null;
		return cust;
	}
	
	public List<Customer> getAllCustomer() {
		List<Customer> cust = custDao.findAll();
		System.out.println("cust  : "+cust);
		return cust;
	}
	
	public Customer saveCustomerDetails(Customer customer) {
		return custDao.save(customer);
	}

}
