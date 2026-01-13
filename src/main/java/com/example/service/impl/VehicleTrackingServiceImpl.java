package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Vehicle;
import com.example.repository.VehicleRepository;
import com.example.service.VehicleTrackingService;

@Service
public class VehicleTrackingServiceImpl implements VehicleTrackingService{

    @Autowired
    private VehicleRepository vehicleRepository;

    public void updateVehicleLocation(Vehicle vehicle){
        vehicle.setCurrentLatitude(vehicle.getCurrentLatitude()+0.0005);
        vehicle.setCurrentLongitude(vehicle.getCurrentLongitude()+0.005);
        vehicleRepository.save(vehicle);
    }
}