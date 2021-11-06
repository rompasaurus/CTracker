package com.CTracker.repository;

import com.CTracker.model.Park;
import com.CTracker.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;


public interface ParkRepository extends JpaRepository<Park, Long> {
    Optional<Park> findById(Long id);
}

