package com.example.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BillingSummaryDto;
import com.example.service.BillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {
    
    @Autowired
    private BillingService billingService;

    @GetMapping("/summary")
    public BillingSummaryDto getBillingSummary(@RequestParam String startDate, @RequestParam String endDate){
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start= LocalDate.parse(startDate, formatter);
        LocalDate end= LocalDate.parse(endDate, formatter);
        return billingService.getBillingSummary(start, end);
    }
    
}
