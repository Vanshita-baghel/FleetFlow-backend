package com.example.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.entity.Trip;
import com.example.entity.Vehicle;
import com.example.entity.Vehicle.Status;
import com.example.repository.TripRepository;
import com.example.repository.VehicleRepository;
import com.example.util.DistanceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class VehicleTrackingScheduler{
    
    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;
    private final Logger logger= LoggerFactory.getLogger(VehicleTrackingScheduler.class);

    public VehicleTrackingScheduler(
            TripRepository tripRepository,
            VehicleRepository vehicleRepository) {
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Scheduled(fixedRate= 10000)
    public void trackVehicles(){
        List<Trip> ongoingTrips= tripRepository.findByStatus(com.example.entity.Trip.Status.valueOf("ONGOING"));
        
        for(Trip trip: ongoingTrips){
            logger.info("------------------->Tracking vehicle for trip id: "+ trip.getTripId());
            Vehicle vehicle= trip.getVehicle();
            vehicle.setCurrentLatitude(vehicle.getCurrentLatitude()+1.0);
            vehicle.setCurrentLongitude(vehicle.getCurrentLongitude()+1.0);
            vehicleRepository.save(vehicle);
            logger.info("vehicle "+vehicle.getVehicleNumber()+ "current location updated to ( "+ vehicle.getCurrentLatitude()+" "+
                                            vehicle.getCurrentLongitude());
            double distance=1.0;
            try {
                distance = DistanceUtil.calculateDistance(vehicle.getCurrentLatitude(), vehicle.getCurrentLongitude(), 
                                trip.getDestinationLatitude(), trip.getDestinationLongitude());
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("Distance of vehicle "+ vehicle.getVehicleNumber()+ " from destination: "+distance + " km");
            
            if(distance<=0.5){
                logger.info(" vehicle "+ vehicle.getVehicleNumber()+ "completed its trip "+ trip.getTripId());
                trip.setStatus(com.example.entity.Trip.Status.valueOf("COMPLETED"));
                trip.setEndDate(LocalDate.now());

                vehicle.setStatus(Status.valueOf("AVAILABLE"));

                tripRepository.save(trip);
                vehicleRepository.save(vehicle);
            }
        }
    }
}