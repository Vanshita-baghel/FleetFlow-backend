package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingVehicleSummaryDto{
    private String vehicleId;
    private String vehicleType;
    private int numTrips;
    private double totalFreight;
}