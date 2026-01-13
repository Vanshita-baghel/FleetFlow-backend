package com.example.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CustomerDto;
import com.example.dto.TripResponseDto;
import com.example.entity.Customer;
import com.example.entity.Trip;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id){
        return customerRepository.findById(id).get();
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, CustomerDto customerDto){
        Customer newCustomer= customerRepository.findById(id).get();
        newCustomer.setName(customerDto.getName());
        newCustomer.setPhoneNumber(customerDto.getPhoneNumber());

        return customerRepository.save(newCustomer);
    }

    public String deleteCustomer(Long id){
        customerRepository.deleteById(id);
        return "deleted successfully";
    }

    public List<TripResponseDto> getAllTripsByCustomerId(Long customerId){
        
        Customer customer= customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException());
        List<Trip> trips= customer.getTrips();
        List<TripResponseDto> tripResponseDtos = trips.stream()
            .map(trip-> modelMapper.map(trip, TripResponseDto.class))
            .toList();
        return tripResponseDtos;
    }

}