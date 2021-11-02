package com.CTracker.controller;

import com.CTracker.model.Park;
import com.CTracker.model.Ride;
import com.CTracker.service.ParkService;
import com.CTracker.service.RideService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/park")
@AllArgsConstructor
@Slf4j
public class ParkController {
    private final ParkService parkService;
    @GetMapping
    public ResponseEntity<List<Park>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parkService.getAllParks());
    }
}
