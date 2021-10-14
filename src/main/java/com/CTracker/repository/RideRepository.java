package com.CTracker.repository;

import com.CTracker.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


public interface RideRepository extends JpaRepository<Ride, Long> {
}

