package com.example.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.PaymentResponseDto;
import com.example.dto.TripRequestDto;
import com.example.dto.TripResponseDto;
import com.example.entity.Customer;
import com.example.entity.Driver;
import com.example.entity.Payment;
import com.example.entity.Trip;
import com.example.entity.Vehicle;
import com.example.entity.Trip.Status;
// import com.example.entity.Driver.Status;
import com.example.repository.TripRepository;
import com.example.service.CustomerService;
import com.example.service.DriverService;
import com.example.service.TripService;
import com.example.service.VehicleService;

@Service
public class TripServiceImpl implements TripService{

    @Autowired
    private DriverService driverService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TripResponseDto createTrip(TripRequestDto tripRequest){

        Vehicle vehicle= vehicleService.getVehicleById(tripRequest.getVehicleId());

        Driver driver= driverService.getDriver(tripRequest.getDriverId());

        Customer customer = customerService.getCustomer(tripRequest.getCustomerId());

        Trip newTrip= new Trip();

        newTrip.setVehicle(vehicle);
        newTrip.setDriver(driver);
        newTrip.setCustomer(customer);
        newTrip.setFromLocation(tripRequest.getPickupLocation());
        newTrip.setDestination(customer.getAddress());
        newTrip.setMaterialWeight(tripRequest.getMaterialWeight());
        newTrip.setFreightAmount(tripRequest.getFreightAmount());
        newTrip.setAdvanceAmount(tripRequest.getAdvance());
        newTrip.setBalanceAmount(tripRequest.getFreightAmount()-tripRequest.getAdvance());
        newTrip.setStartDate(LocalDate.now());
    // newTrip.setEndDate( LocalDate.now().plusDays(tripRequest.getExpectedDays())); -> will be update automatically by scheduler
        newTrip.setStatus(Status.ONGOING);

        newTrip.setDestinationLatitude(tripRequest.getDestinationLatitude());
        newTrip.setDestinationLongitude(tripRequest.getDestinationLongitude());

        //now update the status of driver and vehicle
        driverService.updateStatus(tripRequest.getDriverId(), "on_duty");
        vehicleService.updateStatus(tripRequest.getVehicleId(), "on_duty");

        tripRepository.save(newTrip);

        TripResponseDto tripResponse = new TripResponseDto();
        tripResponse.setTripId(newTrip.getTripId());
        tripResponse.setVehicleNumber(vehicle.getVehicleNumber());
        tripResponse.setDriverName(driver.getName());
        tripResponse.setCustomerName(customer.getName());
        tripResponse.setPickupLocation(tripRequest.getPickupLocation());
        tripResponse.setDropLocation(newTrip.getDestination());
        tripResponse.setFreightAmount(tripRequest.getFreightAmount());
        tripResponse.setStartDate(newTrip.getStartDate());
        tripResponse.setEndDate(newTrip.getEndDate());
        tripResponse.setStatus(newTrip.getStatus());

        return tripResponse;
    }

    public String completeTrip(Long id){
        Trip trip= tripRepository.findById(id).get();
        trip.setStatus(Status.COMPLETED);
        trip.setEndDate(LocalDate.now());

        //update driver and vehicle status
        driverService.updateStatus(trip.getDriver().getDriverId(), "available");
        vehicleService.updateStatus(trip.getVehicle().getVehicleId(), "available");

        tripRepository.save(trip);

        return "Trip status updated to COMPLETED";
    }

    public Trip getTripById(Long id){
        return tripRepository.findById(id).get();
    }

    public List<PaymentResponseDto> getPaymentsByTripId(Long tripId){
        Trip trip= tripRepository.findById(tripId).orElseThrow(()-> new RuntimeException("trip not found"));
        List<Payment> payments= trip.getPayments();
        List<PaymentResponseDto> paymentDtos= payments.stream()
            .map(payment -> modelMapper.map(payment, PaymentResponseDto.class))
            .toList();
        return paymentDtos;
    }

    public TripResponseDto cancelTrip(Long id){
        Trip trip= tripRepository.findById(id).orElseThrow(()-> new RuntimeException("trip not found"));
        if(trip.getStatus()== Status.COMPLETED){
            throw new RuntimeException("cnnot cancel a completed trip");
        }
        trip.setStatus(Status.CANCELLED);

        Driver driver= trip.getDriver();
        Vehicle vehicle= trip.getVehicle();
        driverService.updateStatus(driver.getDriverId(), "available");
        vehicleService.updateStatus(vehicle.getVehicleId(), "available");

        tripRepository.save(trip);
        return modelMapper.map(trip, TripResponseDto.class);
    }

    public List<TripResponseDto> getTripByStatus(String status){
        List<Trip> trips= tripRepository.findByStatus(Status.valueOf(status.toUpperCase()));
        List<TripResponseDto> tripDtos= trips.stream()
            .map(trip-> modelMapper.map(trip, TripResponseDto.class)).toList();
            //set the vehicle number 
        for(TripResponseDto dto: tripDtos){
            dto.setVehicleNumber(
                tripRepository.findById(dto.getTripId()).get().getVehicle().getVehicleNumber()
            );
        }
        return tripDtos;
    }

    //get trips by date range and those trips which are completed or ongoing
    public List<TripResponseDto> getTripsByDateRange(String startDate, String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start= LocalDate.parse(startDate, formatter);
        LocalDate end= LocalDate.parse(endDate, formatter);
        List<Trip> trips= tripRepository.findByStartDateBetween(start, end);
        return trips.stream()
            .map(trip-> modelMapper.map(trip, TripResponseDto.class))
            .toList();
    }

    public TripResponseDto updateTripStatus(Long tripId, String status){
        Trip trip= tripRepository.findById(tripId).orElseThrow(()-> new RuntimeException("trip not found"));
        trip.setStatus(Status.valueOf(status.toUpperCase()));
        tripRepository.save(trip);
        return modelMapper.map(trip, TripResponseDto.class);
    }

}

