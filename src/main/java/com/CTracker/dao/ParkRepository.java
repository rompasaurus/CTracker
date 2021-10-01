package com.CTracker.dao;

import com.CTracker.entity.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ParkRepository extends JpaRepository<Park, Long> {
}

