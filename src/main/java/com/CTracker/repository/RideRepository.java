package com.CTracker.repository;

import com.CTracker.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


public interface RideRepository extends JpaRepository<Ride, Long> {
    //List<Ride> getAllRides();
}

