package com.CTracker.controller;

import com.CTracker.dto.RideRequest;
import com.CTracker.model.Ride;
import com.CTracker.service.RideService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ride")
@AllArgsConstructor
@Slf4j
public class RideController {
    private final RideService rideService;
    @GetMapping
    public ResponseEntity<List<Ride>> getAllRides() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rideService.getAllRides());
    }
    @PostMapping
    public ResponseEntity<Void> createRide(@RequestBody RideRequest rideRequest) {
        log.debug("Saving Ride vie RideRequest: "+ rideRequest);
        rideService.save(rideRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{parkId}")
    public ResponseEntity<List<Ride>> getAllRidesFromPark(@PathVariable Long parkId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rideService.getAllRidesByPark(parkId));
    }
}