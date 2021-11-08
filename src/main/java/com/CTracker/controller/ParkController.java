package com.CTracker.controller;

import com.CTracker.dto.ParkRequest;
import com.CTracker.dto.PostRequest;
import com.CTracker.model.Park;
import com.CTracker.model.Ride;
import com.CTracker.service.ParkService;
import com.CTracker.service.RideService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/park")
@AllArgsConstructor
@Slf4j
public class ParkController {
    private final ParkService parkService;
    private final RideService rideService;
    @GetMapping
    public ResponseEntity<List<Park>> getAllParks() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parkService.getAllParks());
    }
    @PostMapping
    public ResponseEntity<Void> createPark(@RequestBody ParkRequest parkRequest) {
        parkService.save(parkRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/byRide/{rideId}")
    public ResponseEntity<Park> getAllParks(@PathVariable Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ride.getPark());
    }
}
