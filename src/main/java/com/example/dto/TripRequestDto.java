package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripRequestDto {

    private Long vehicleId;

    private Long driverId;

    private Long customerId;

    private double materialWeight;      //in tons

    private String pickupLocation;

    private String dropLocation;
    
    private double freightAmount;

    private double advance;

    private double destinationLatitude;

    private double destinationLongitude;

}
