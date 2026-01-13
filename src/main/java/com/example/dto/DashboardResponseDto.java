package com.example.dto;

import java.util.List;

import lombok.Data;

@Data
public class DashboardResponseDto{

    private DriverStats driverStats;
    private TripStats tripStats;
    private VehicleStats vehicleStats;
    private PaymentStats paymentStats;
    private List<String> alerts;

    @Data
    public static class DriverStats{
        private int totalDrivers;
        private int availableDrivers;
        private int onDutyDrivers;
        private int onLeaveDrivers;
    }

    @Data
    public static class TripStats{
        private int totalTrips;
        private int completedTrips;
        private int ongoingTrips;
        private int cancelledTrips;
        private int todayTrips;
    }

    @Data
    public static class VehicleStats{
        private int totalVehicles;
        private int availableVehicles;
        private int onDutyVehicles;
        private int underMaintenanceVehicles;
    }

    @Data
    public static class PaymentStats{
        private double totalFreight;
        private double pendingPayments;
        private double receivedPayments;
    }
}