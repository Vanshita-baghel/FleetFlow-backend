package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentStatus=com.example.entity.Payment.PaymentStatus.COMPLETED  ")
    public Double getTotalReceivedPayment();
}