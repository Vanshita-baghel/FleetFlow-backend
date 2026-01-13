package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Vehicle;
import com.example.entity.Vehicle.Status;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

    public List<Vehicle> findByStatusAndCapacityGreaterThanEqual(Status status, double cap);

    public int countByStatus(Status status);
}