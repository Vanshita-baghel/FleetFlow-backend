package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PaymentRequestDto;
import com.example.dto.PaymentResponseDto;
import com.example.service.PaymentService;
import com.example.service.TripService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController{

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TripService tripService;

    
    @PostMapping("/recordPayment")
    public PaymentResponseDto recordPayment(@RequestBody PaymentRequestDto dto){
        return paymentService.recordPayment(dto);
    }

    @GetMapping("/getPaymentById")
    public PaymentResponseDto getPaymentById(@RequestParam Long id){
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/trip/history")
    public List<PaymentResponseDto> getPaymentsByTripId(@RequestParam Long tripId){
        return tripService.getPaymentsByTripId(tripId);
    }
}