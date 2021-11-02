package com.CTracker.service;

import com.CTracker.dto.PostResponse;
import com.CTracker.model.Ride;
import com.CTracker.repository.RideRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class RideService {
    private final RideRepository rideRepository;

    @Transactional(readOnly = true)
    public List<Ride> getAllRides() {
        return rideRepository.findAll()
                .stream()
                .collect(toList());
    }
}
