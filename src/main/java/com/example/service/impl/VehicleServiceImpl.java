package com.example.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.TripResponseDto;
import com.example.dto.VehicleDto;
import com.example.entity.Trip;
import com.example.entity.Vehicle;
import com.example.repository.VehicleRepository;
import com.example.service.VehicleService;
import com.example.entity.Vehicle.Status;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;

    //crud
    public Vehicle addVehicle(Vehicle vehicle){
        if(vehicle.getStatus()==null){
            vehicle.setStatus(Status.AVAILABLE);
        }
        Vehicle savedVehicle=vehicleRepository.save(vehicle);
        return savedVehicle;
    }

    public Vehicle getVehicleById(long id){
        Vehicle vehicle= vehicleRepository.findById(id).orElseThrow(()->new RuntimeException("vehicle not found"));
        return vehicle;
    }

    public Vehicle updateVehicle(long id, VehicleDto updatedVehicle){
        Vehicle vehicle= vehicleRepository.findById(id).get();
        vehicle.setVehicleNumber(vehicle.getVehicleNumber());
        vehicle.setType(vehicle.getType());
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public String deleteVehicle(long id){
        Vehicle vehicle= vehicleRepository.findById(id).get();
        vehicleRepository.delete(vehicle);
        return "deleted successfully";
    }

    public List<Vehicle> getAllVehicles(){
        List<Vehicle> vehicles= vehicleRepository.findAll();
        return vehicles;
    }

    public List<Vehicle> findVehicle(String status, double capacity){
        return vehicleRepository.findByStatusAndCapacityGreaterThanEqual(Status.valueOf(status.trim().toUpperCase()), capacity);
    }

    public Vehicle updateStatus(Long id, String newStatus){
        Vehicle vehicle= vehicleRepository.findById(id).get();
        vehicle.setStatus(Status.valueOf(newStatus.trim().toUpperCase()));
        return vehicleRepository.save(vehicle);
    }

    public List<TripResponseDto> getAllTripsByVehicleId(Long vehicleId){
        Vehicle vehicle= vehicleRepository.findById(vehicleId).get();
        List<Trip> trips= vehicle.getTrips();
        return trips.stream()
            .map(trip-> modelMapper.map(trip, TripResponseDto.class))
            .toList();
    }

}