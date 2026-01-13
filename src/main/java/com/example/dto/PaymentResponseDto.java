package com.example.dto;

import java.time.LocalDate;

import com.example.entity.Payment.PaymentMode;
import com.example.entity.Payment.PaymentStatus;

import lombok.Data;

@Data
public class PaymentResponseDto{

    private Long id;

    private double amount;

    private String paymentType;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;

    private LocalDate tripDate;
    private String notes;

    private Long tripId;
    private double tripBalanceLeft;
}