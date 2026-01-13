package com.example.service;

import java.util.List;

import com.example.dto.PaymentRequestDto;
import com.example.dto.PaymentResponseDto;

public interface PaymentService{

    public PaymentResponseDto recordPayment(PaymentRequestDto dto);

    public PaymentResponseDto getPaymentById(Long id);

}