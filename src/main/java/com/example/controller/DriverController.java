package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.DriverDto;
import com.example.entity.Driver;
import com.example.service.DriverService;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/addDriver")
    public Driver addDriver(@RequestBody Driver driver){
        return driverService.addDriver(driver);
    }
    
    @GetMapping("/getById")
    public Driver getDriver(@RequestParam Long id ){
        return driverService.getDriver(id);
    }

    @GetMapping("/getAllDrivers")
    public List<Driver> getAll(){
        return driverService.getAllDrivers();
    }

    @PutMapping("/updateDriver")
    public Driver updateDriver(@RequestParam Long id, @RequestBody DriverDto driverDto){
        return driverService.updateDriver(id, driverDto);
    }

    @DeleteMapping("/deleteDriver")
    public String deleteDriver(@RequestParam Long id){
        return driverService.deleteDriver(id);
    }



                                                //FEATURES
                                                
    @GetMapping("/getAvailableDrivers")                             //DONE
    public List<DriverDto> getAvailableDrivers(){
        List<Driver> availableDrivers= driverService.findByStatus("available");

        List<DriverDto> driverDtos= availableDrivers.stream().map(driver-> modelMapper.map(driver, DriverDto.class)).toList();

        return driverDtos;
    }
}