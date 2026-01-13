package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.entity.Trip;
import com.example.entity.Trip.Status;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long>{

    public List<Trip> findByStatus(Status status);

    // public List<Trip> findByVehicleId(Long vehicleId);

    public List<Trip> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("""
        SELECT 
            t.vehicle.vehicleNumber,
            t.vehicle.type,
            COUNT(t.tripId),
            SUM(t.freightAmount)
        FROM Trip t
        WHERE t.startDate BETWEEN :start AND :end
        GROUP BY t.vehicle.vehicleNumber, t.vehicle.type
        """)
    List<Object[]> getBillingSummary(LocalDate start, LocalDate end);

    public int countByStatus(com.example.entity.Trip.Status status);

    @Query("SELECT COUNT(t) FROM Trip t WHERE t.startDate = CURRENT_DATE")
    public int countTodayTrips();

    @Query("SELECT SUM(t.freightAmount) FROM Trip t WHERE t.status=com.example.entity.Trip.Status.COMPLETED " )
    public double getTotalFreight();

}