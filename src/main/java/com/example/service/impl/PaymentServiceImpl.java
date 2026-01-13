package com.example.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.PaymentRequestDto;
import com.example.dto.PaymentResponseDto;
import com.example.entity.Payment;
import com.example.entity.Payment.PaymentStatus;
import com.example.entity.Trip.Status;
import com.example.entity.Trip;
import com.example.repository.PaymentRepository;
import com.example.repository.TripRepository;
import com.example.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PaymentResponseDto recordPayment(PaymentRequestDto dto){
        Trip trip= tripRepository.findById(dto.getTripId()).orElseThrow(()-> new RuntimeException("trip not found"));

        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPaymentType(dto.getPaymentType());
        payment.setPaymentMode(dto.getPaymentMode());
        // payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setNotes(dto.getNotes());
        payment.setTrip(trip);
        payment.setPaymentDate(LocalDate.now());
        
        Payment savedPayment= paymentRepository.save(payment);

        trip.setBalanceAmount(trip.getBalanceAmount()-dto.getAmount());
        tripRepository.save(trip);

        if(trip.getBalanceAmount()==0){
            // trip.setStatus(Status.COMPLETED);
            savedPayment.setPaymentStatus(PaymentStatus.COMPLETED);
            paymentRepository.save(savedPayment);
        }
        else{
            // trip.setStatus(Status.ONGOING);
            savedPayment.setPaymentStatus(PaymentStatus.PENDING);
            paymentRepository.save(savedPayment);
        }

        PaymentResponseDto responseDto= new PaymentResponseDto();
        responseDto.setId(savedPayment.getId());
        responseDto.setAmount(savedPayment.getAmount());
        responseDto.setPaymentType(savedPayment.getPaymentType().toString());
        responseDto.setPaymentMode(savedPayment.getPaymentMode());
        responseDto.setPaymentStatus(savedPayment.getPaymentStatus());
        responseDto.setTripDate(trip.getStartDate());
        responseDto.setNotes(savedPayment.getNotes());
        responseDto.setTripId(trip.getTripId());
        responseDto.setTripBalanceLeft(trip.getBalanceAmount());

        return responseDto;
    }

    public PaymentResponseDto getPaymentById(Long id){
        Payment payment = paymentRepository.findById(id).orElseThrow(()-> new RuntimeException("payment not found"));
        Trip trip= payment.getTrip();
        return modelMapper.map(payment, PaymentResponseDto.class);
    }

    
}