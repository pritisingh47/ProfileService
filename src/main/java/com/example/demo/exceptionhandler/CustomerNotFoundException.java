package com.example.demo.exceptionhandler;

public class CustomerNotFoundException extends RuntimeException{
public CustomerNotFoundException(int id) {
	super(String.format("Customer with id %d not found", id));
}
}
