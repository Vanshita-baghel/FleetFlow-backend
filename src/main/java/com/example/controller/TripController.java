package com.example.controller;

import com.example.service.TripService;
import com.example.dto.DriverDto;
import com.example.entity.Driver;
import com.example.entity.Trip;
import com.example.entity.Trip.Status;
import com.example.repository.TripRepository;
import com.example.dto.TripRequestDto;
import com.example.dto.TripResponseDto;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/trip")
public class TripController {
    
    @Autowired
    private TripService tripService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TripRepository tripRepository;

    @PostMapping("/createNew")                                                      //--
    public TripResponseDto createTrip(@RequestBody TripRequestDto dto){
        return tripService.createTrip(dto);
    }

    @GetMapping("/getTrip")                                                     //--   
    public Trip getTripById(@RequestParam Long id){
        return tripService.getTripById(id);
    }

    @PutMapping("/completeTrip")                      //--
    public String updateTripStatus(@RequestParam Long id){
        return tripService.completeTrip(id);
    }

    @PutMapping("/cancelTrip")                       //--    
    public TripResponseDto cancelTrip(@RequestParam Long id){
        return tripService.cancelTrip(id);
    }

    @GetMapping("/getByStatus")                     //--
    public List<TripResponseDto> getTripByStatus(@RequestParam String status){
        // Status enumStatus = Status.valueOf(status.toUpperCase());
        return tripService.getTripByStatus(status);
    }

    @GetMapping("/getByDateRange")
    public List<TripResponseDto> getTripsByDateRange(@RequestParam String startDate, @RequestParam String endDate){
        return tripService.getTripsByDateRange(startDate, endDate);
    }

    // @DeleteMapping("/deleteTrip")
    // public String deleteTrip(@RequestParam Long id){
    //     TripRepository tripRepository;
    //     Trip trip= tripRepository.findById(id).orElseThrow(()-> new RuntimeException("Trip not found"));
    //     tripRepository.delete(trip);
    //     return "Trip deleted successfully";
    // }

}
