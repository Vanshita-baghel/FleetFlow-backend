package com.example.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trip{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name="driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    private String fromLocation;
    private String destination;
    private String material;
    private double materialWeight;
    private Double freightAmount;
    private Double advanceAmount;
    private Double balanceAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private String remarks;

    //for vehicle tracking
    private Double destinationLatitude;
    private Double destinationLongitude;

    @OneToMany(mappedBy="trip", cascade = CascadeType.ALL, orphanRemoval= true)
    List<Payment> payments;

    public enum Status{
        ONGOING,
        COMPLETED,
        CANCELLED
    }
}