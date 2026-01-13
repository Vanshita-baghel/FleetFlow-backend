package com.example.service.impl;

import java.util.ArrayList;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.DashboardResponseDto;
import com.example.entity.Driver.Status;
import com.example.repository.DriverRepository;
import com.example.repository.PaymentRepository;
import com.example.repository.TripRepository;
import com.example.repository.VehicleRepository;
import com.example.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService{
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public DashboardResponseDto getDashboard(){

        DashboardResponseDto dashboard = new DashboardResponseDto();

        DashboardResponseDto.DriverStats driverStats= new DashboardResponseDto.DriverStats();
        driverStats.setTotalDrivers(driverRepository.findAll().size());
        driverStats.setAvailableDrivers(driverRepository.countByStatus(Status.valueOf("AVAILABLE")));
        driverStats.setOnDutyDrivers(driverRepository.countByStatus(Status.valueOf("ON_DUTY")));
        driverStats.setOnLeaveDrivers(driverRepository.countByStatus(Status.valueOf("ON_LEAVE")));
        dashboard.setDriverStats(driverStats);

        DashboardResponseDto.TripStats tripStats= new DashboardResponseDto.TripStats();
        tripStats.setTotalTrips(tripRepository.findAll().size());
        tripStats.setCompletedTrips(tripRepository.countByStatus(com.example.entity.Trip.Status.valueOf("COMPLETED")));
        tripStats.setOngoingTrips(tripRepository.countByStatus(com.example.entity.Trip.Status.valueOf("ONGOING")));
        tripStats.setCancelledTrips(tripRepository.countByStatus(com.example.entity.Trip.Status.valueOf("CANCELLED")));
        tripStats.setTodayTrips(tripRepository.countTodayTrips());
        dashboard.setTripStats(tripStats);

        DashboardResponseDto.VehicleStats vehicleStats= new DashboardResponseDto.VehicleStats();
        vehicleStats.setTotalVehicles(vehicleRepository.findAll().size());
        vehicleStats.setAvailableVehicles(vehicleRepository.countByStatus(com.example.entity.Vehicle.Status.valueOf("AVAILABLE")));    
        vehicleStats.setOnDutyVehicles(vehicleRepository.countByStatus(com.example.entity.Vehicle.Status.valueOf("ON_DUTY")));
        vehicleStats.setUnderMaintenanceVehicles(vehicleRepository.countByStatus(com.example.entity.Vehicle.Status.valueOf("UNDER_MAINTENANCE")));
        dashboard.setVehicleStats(vehicleStats);

        DashboardResponseDto.PaymentStats paymentStats= new DashboardResponseDto.PaymentStats();
        paymentStats.setTotalFreight(tripRepository.getTotalFreight());
        paymentStats.setReceivedPayments(paymentRepository.getTotalReceivedPayment()==null ? 0: paymentRepository.getTotalReceivedPayment());
        paymentStats.setPendingPayments(paymentStats.getTotalFreight() - paymentStats.getReceivedPayments());
        dashboard.setPaymentStats(paymentStats);

        dashboard.setAlerts(generateAlerts());

        return dashboard;
    }

    public List<String> generateAlerts(){
        List<String> alerts= new ArrayList<>();

        alerts.add("insurance expiring soon");
        alerts.add("maintenance due");

        return alerts;
    }
}