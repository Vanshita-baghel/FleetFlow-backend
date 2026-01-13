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

import com.example.dto.TripResponseDto;
import com.example.dto.VehicleDto;
import com.example.entity.Vehicle;
import com.example.service.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController{

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getById")
    public Vehicle getVehicle(@RequestParam Long id){
        return vehicleService.getVehicleById(id);
    }   

    @GetMapping("/getAllVehicles")
    public List<Vehicle> getAllVehicle(){
        return vehicleService.getAllVehicles();
    }

    @PostMapping("/addVehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.addVehicle(vehicle);
    }

    @PutMapping("/updateVehicle")
    public Vehicle updateVehicle(@RequestParam Long id, @RequestBody VehicleDto vehicleDto){
        return vehicleService.updateVehicle(id, vehicleDto);
    }

    @DeleteMapping("/deleteVehicle")
    public String deleteVehicle(@RequestParam Long id){
        return vehicleService.deleteVehicle(id);
    }

    //available vehicle with suitable capacity
    @GetMapping("/suitableVehicle")
    public List<VehicleDto> findSuitableVehicle(@RequestParam String status ,@RequestParam double weight){
        List<Vehicle> vehicles= vehicleService.findVehicle(status, weight);
        List<VehicleDto> vehicleDtos= vehicles.stream().map(vehicle -> modelMapper.map(vehicle, VehicleDto.class)).toList();

        return vehicleDtos;
    }

    @GetMapping("/getAllTrips")
    public List<TripResponseDto> getAllTripsByVehicleId(@RequestParam Long vehicleId){
        return vehicleService.getAllTripsByVehicleId(vehicleId);
    }
    
}