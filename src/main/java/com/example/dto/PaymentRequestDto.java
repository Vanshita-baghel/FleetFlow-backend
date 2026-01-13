package com.example.dto;

import com.example.entity.Payment.PaymentMode;
import com.example.entity.Payment.PaymentStatus;
import com.example.entity.Payment.PaymentType;

import lombok.Data;

@Data
public class PaymentRequestDto{

    private Long tripId;

    private double amount;

    private PaymentType paymentType;        //full, partial, advance

    private PaymentMode paymentMode;        //upi, cash, card, net banking

    // private PaymentStatus paymentStatus;    //PENDING, COMPLETED    

    private String notes;

}
