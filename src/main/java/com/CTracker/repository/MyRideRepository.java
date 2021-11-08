package com.CTracker.repository;

import com.CTracker.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MyRideRepository extends JpaRepository<MyRide, Long> {
    Optional<MyRide> findById(Long id);
    List<MyRide> findAllByUser(User user);
}

