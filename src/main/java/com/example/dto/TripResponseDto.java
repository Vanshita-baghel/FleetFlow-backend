package com.example.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.entity.Trip.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripResponseDto {

    private Long tripId;

    private String vehicleNumber;

    private String driverName;

    private String customerName;

    private String pickupLocation;

    private String dropLocation;

    private double freightAmount;
    
    private LocalDate startDate;

    private LocalDate endDate;

    private Status status;
}
