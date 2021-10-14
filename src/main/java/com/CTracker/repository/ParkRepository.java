package com.CTracker.repository;

import com.CTracker.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


public interface ParkRepository extends JpaRepository<Park, Long> {
}

