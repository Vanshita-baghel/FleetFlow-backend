package com.example.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle{

    @Id
    private Long vehicleId;

    private String vehicleNumber;

    private String model;

    private String type;    //mini truck, truck, container truck

    private double capacity;  //in tons

    @Enumerated(EnumType.STRING)
    private Status status;  //available, on-duty, under-maintenance

    private LocalDate insuranceExpiryDate;

    private LocalDate fitnessExpiryDate;

    private LocalDate pollutionExpiryDate;

    private String remarks;

    private Double currentLatitude;

    private Double currentLongitude;

    @OneToMany(mappedBy = "vehicle")
    @JsonIgnore
    private List<Trip> trips;

    public enum Status{
        AVAILABLE,
        ON_DUTY,
        UNDER_MAINTENANCE
    }
}