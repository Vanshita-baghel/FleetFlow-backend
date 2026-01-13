package com.example.service;

import java.util.List;

import com.example.dto.CustomerDto;
import com.example.dto.TripResponseDto;
import com.example.entity.Customer;

public interface CustomerService {
    
    
    public Customer addCustomer(Customer customer);

    public Customer getCustomer(Long id);

    public List<Customer> getAllCustomers();

    public Customer updateCustomer(Long id, CustomerDto customerDto);

    public String deleteCustomer(Long id);

    public List<TripResponseDto> getAllTripsByCustomerId(Long customerId);
}
