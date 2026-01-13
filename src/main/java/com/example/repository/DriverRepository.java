package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Driver;
import com.example.entity.Driver.Status;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{

    public List<Driver> findByStatus(Status status);

    public int countByStatus(Status status);
    
}