package com.CTracker.service;

import com.CTracker.dto.RideRequest;
import com.CTracker.exceptions.ParkNotFoundException;
import com.CTracker.exceptions.SubredditNotFoundException;
import com.CTracker.mapper.RideMapper;
import com.CTracker.model.Park;
import com.CTracker.model.Ride;
import com.CTracker.repository.ParkRepository;
import com.CTracker.repository.RideRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class RideService {
    private final RideRepository rideRepository;
    private final ParkRepository parkRepository;
    private final RideMapper rideMapper;

    @Transactional(readOnly = true)
    public List<Ride> getAllRides() {
        return rideRepository.findAll()
                .stream()
                .collect(toList());
    }

    public Ride save(RideRequest rideRequest) {
        Park pullPark = parkRepository.findById(rideRequest.getParkId())
                .orElseThrow(() -> new ParkNotFoundException(rideRequest.toString()));
        log.debug("Pulled Park from RideRequest: "+ pullPark);
        Ride savedRide = rideRepository.save(rideMapper.map(rideRequest,pullPark));
        log.debug("Saving Ride: "+ savedRide);
        return savedRide;
    }
}
