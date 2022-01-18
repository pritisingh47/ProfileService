package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer>{
	 Optional<Customer> findById(int id);
	 
	 
}
