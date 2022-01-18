package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.exceptionhandler.CustomerNotFoundException;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Value("${message}")
	String message;
	
	

	@RequestMapping(value="/getCustomerDetails", method=RequestMethod.GET)
	public Customer getCustomerById(@RequestParam int id) {
		System.out.println("custId is :"+id);
		if (!customerService.getCustomerById(id).isPresent()) {
			throw new CustomerNotFoundException(id);
		}
		return customerService.getCustomerById(id).get();
	}
	
	@RequestMapping(value="/getAllCustomerDetails", method=RequestMethod.GET,
			produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasRole('ROLE_operator')")
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomer();
	}
	
	@RequestMapping(value="/saveCustomerDetails",method=RequestMethod.POST,
			produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAuthority('create_profile')")
	public ResponseEntity<Customer> saveCustomerDetails(@RequestBody Customer customer){
		System.out.println("customer"+customer);
		Customer cust = customerService.saveCustomerDetails(customer);
		return new ResponseEntity(cust,HttpStatus.CREATED);
	}
	
	@RequestMapping("/getWelcomeMessage")
	public String getWelcomeMessage() {
		return message;
	}
	
	
}
