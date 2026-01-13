package com.example.entity;

import java.time.LocalDate;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Driver{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private String name;

    @Column(nullable=false)
    private String phoneNumber;

    @Column(nullable=false)
    private String licenseNumber;

    @Column(nullable=false)
    private LocalDate licenseExpiryDate;

    private double salary;

    @Enumerated(EnumType.STRING)
    private Status status;      //available, on duty, on leave

    private String remarks;

    @OneToMany(mappedBy = "driver")
    @JsonIgnore
    private List<Trip> trips;

    public enum Status{
        AVAILABLE,
        ON_DUTY,
        ON_LEAVE
    }

}