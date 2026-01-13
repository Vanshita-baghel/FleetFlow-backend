package com.example.service;

import java.util.List;

import com.example.dto.PaymentResponseDto;
import com.example.dto.TripRequestDto;
import com.example.dto.TripResponseDto;
import com.example.entity.Trip;

// import com.example.dto.TripRequestDto;
// import com.example.dto.TripResponseDto;
// import com.example.entity.Trip;
// import com.example.entity.Driver;

// import java.util.List;

public interface TripService {
    
    public TripResponseDto createTrip(TripRequestDto tripRequest);

    public Trip getTripById(Long id);

    public String completeTrip(Long id);

    public List<PaymentResponseDto> getPaymentsByTripId(Long tripId);

    public TripResponseDto updateTripStatus(Long tripId, String status);

    public TripResponseDto cancelTrip(Long id);

    public List<TripResponseDto> getTripByStatus(String status);

    public List<TripResponseDto> getTripsByDateRange(String startDate, String endDate);
}
