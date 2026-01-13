package com.example.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingSummaryDto{
    private LocalDate billingFrom;
    private LocalDate billingTo;

    private List<BillingVehicleSummaryDto> vehicleSummaries;

    private double grandTotal;

    private int totalTrips;
}