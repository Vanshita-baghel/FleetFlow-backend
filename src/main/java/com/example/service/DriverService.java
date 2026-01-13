package com.example.service;

import java.lang.Thread.State;
import java.util.List;

import com.example.dto.DriverDto;
import com.example.entity.Driver;
// import com.example.entity.Driver.Status;

public interface DriverService {
    
    public Driver addDriver(Driver driver);

    public Driver getDriver(long id);

    public List<Driver> getAllDrivers();

    public Driver updateDriver(Long id, DriverDto newInfo);

    public String deleteDriver(Long id);

    public List<Driver> findByStatus(String status);

    public Driver updateStatus(Long id, String newStatus);

}
