package com.example.service;

import java.util.List;

import com.example.dto.TripResponseDto;
import com.example.dto.VehicleDto;
import com.example.entity.Vehicle;

public interface VehicleService{
    
    //crud for vehicles

    public Vehicle addVehicle(Vehicle vehicle);

    public Vehicle getVehicleById(long id);

    public Vehicle updateVehicle(long id, VehicleDto updatedVehicle);

    public String deleteVehicle(long id);

    public List<Vehicle> getAllVehicles();

    public List<Vehicle> findVehicle(String status, double capacity);

    public Vehicle updateStatus(Long id, String newStatus);

    public List<TripResponseDto> getAllTripsByVehicleId(Long vehicleId);
} 