package com.example.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BillingSummaryDto;
import com.example.dto.BillingVehicleSummaryDto;
import com.example.repository.TripRepository;
import com.example.service.BillingService;

@Service
public class BillingServiceImpl implements BillingService{

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BillingSummaryDto getBillingSummary(LocalDate startDate, LocalDate endDate){
        List<Object[]> rawSummary= tripRepository.getBillingSummary(startDate, endDate);

        List<BillingVehicleSummaryDto> vehicles= rawSummary.stream().map(obj ->{
            BillingVehicleSummaryDto dto= new BillingVehicleSummaryDto();
            dto.setVehicleId((String)obj[0]);
            dto.setVehicleType((String) obj[1]);
            dto.setNumTrips(((Number)obj[2]).intValue());
            dto.setTotalFreight(((Number)obj[3]).doubleValue());
            return dto;
        }).collect(Collectors.toList());

        BillingSummaryDto billingSummaryDto= new BillingSummaryDto();
        billingSummaryDto.setBillingFrom(startDate);
        billingSummaryDto.setBillingTo(endDate);
        billingSummaryDto.setVehicleSummaries(vehicles);
        int totalTrips= vehicles.stream().mapToInt(v-> v.getNumTrips()).sum();
        double grandTotal= vehicles.stream().mapToDouble(v-> v.getTotalFreight()).sum();
        billingSummaryDto.setTotalTrips(totalTrips);
        billingSummaryDto.setGrandTotal(grandTotal);
        return billingSummaryDto;
    }
}