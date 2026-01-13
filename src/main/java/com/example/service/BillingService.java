package com.example.service;

import java.time.LocalDate;

import com.example.dto.BillingSummaryDto;

public interface BillingService{

    BillingSummaryDto getBillingSummary(LocalDate startDate, LocalDate endDate);
    
}