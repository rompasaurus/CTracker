package com.CTracker.controller;

import com.CTracker.dto.ParkRequest;
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
    @GetMapping("/countries")
    public ResponseEntity<List<String>> getAllCountries() {
        log.debug("Pulling all countries: ");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parkService.getAllCountries());
    }
    @GetMapping("/states/{country}")
    public ResponseEntity<List<String>> getAllStatesByCountry(@PathVariable String country) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parkService.getAllStatesByCountry(country));
    }
    @GetMapping("/cities/{state}")
    public ResponseEntity<List<String>> getAllCitiesByState(@PathVariable String state) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parkService.getAllCitiesByState(state));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Park>> getAllParksByCity(@PathVariable String city) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parkService.getAllParksByCity(city));
    }
    @GetMapping("/all/cities")
    public ResponseEntity<List<String>> getAllCitiesByState() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parkService.getAllCities());
    }
    @GetMapping("/state/{state}")
    public ResponseEntity<List<Park>> getAllParksByState(@PathVariable String state) {
        log.debug("Pulling all Parks by state: " + state);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parkService.getAllParksByState(state));
    }
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Park>> getAllParksByCountry(@PathVariable String country) {
        log.debug("Pulling all Parks by state: " + country);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parkService.getAllParksByCountry(country));
    }
}
