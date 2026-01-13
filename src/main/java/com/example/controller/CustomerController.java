package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CustomerDto;
import com.example.dto.TripResponseDto;
import com.example.entity.Customer;
import com.example.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController{

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }
    
    @GetMapping("/getById")
    public Customer getCustomer(@RequestParam Long id ){
        return customerService.getCustomer(id);
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAll(){
        return customerService.getAllCustomers();
    }

    @PutMapping("/updateCustomer")
    public Customer updateCustomer(@RequestParam Long id, @RequestBody CustomerDto customerDto){
        return customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam Long id){
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/getAllTrips")
    public List<TripResponseDto> getAllTripsByCustomerId(@RequestParam Long customerId){
        return customerService.getAllTripsByCustomerId(customerId);
    }
}