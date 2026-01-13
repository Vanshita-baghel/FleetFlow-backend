package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.DriverDto;
import com.example.entity.Driver;
import com.example.entity.Driver.Status;
import com.example.repository.DriverRepository;
import com.example.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;

    public Driver addDriver(Driver driver){
        if(driver.getStatus()==null){
            driver.setStatus(Status.AVAILABLE);
        }
        return driverRepository.save(driver);
    }

    public Driver getDriver(long id){
        return driverRepository.findById(id).get();
    }

    public List<Driver> getAllDrivers(){
        return  driverRepository.findAll();
    }

    public Driver updateDriver(Long id, DriverDto newInfo){
        Driver driver= driverRepository.findById(id).get();
        driver.setName(newInfo.getName());
        driver.setPhoneNumber(newInfo.getPhoneNumber());
        driverRepository.save(driver);
        return driver;
    }

    public String deleteDriver(Long id){
        Driver driver= driverRepository.findById(id).get();
        driverRepository.delete(driver);
        return "Deletion successful";

    }

    public List<Driver> findByStatus(String status){
        Status enumStatus= Status.valueOf(status.trim().toUpperCase());
        return driverRepository.findByStatus(enumStatus);
    }

    public Driver updateStatus(Long id, String newStatus){
        Driver driver= driverRepository.findById(id).get();
        driver.setStatus(Status.valueOf(newStatus.trim().toUpperCase()));
        return driverRepository.save(driver);
    }

    
}