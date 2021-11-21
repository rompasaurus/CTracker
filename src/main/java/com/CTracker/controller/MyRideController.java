package com.CTracker.controller;

import com.CTracker.dto.MyRideRequest;
import com.CTracker.dto.RideRequest;
import com.CTracker.model.MyRide;
import com.CTracker.model.Ride;
import com.CTracker.model.User;
import com.CTracker.service.AuthService;
import com.CTracker.service.MyRideService;
import com.CTracker.service.RideService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/myrides/")
@AllArgsConstructor
@Slf4j
public class MyRideController {
    private final MyRideService myRideService;
    private final AuthService authService;
    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<MyRide>> getAllMyRides(@PathVariable String username) {
        User user = authService.getCurrentUser();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(myRideService.getAll(username));
    }
    @PostMapping("/by-user/{username}")
    public ResponseEntity<Void> createMyRide(@RequestBody MyRideRequest myRideRequest) {
        User user = authService.getCurrentUser();
        myRideRequest.setUserId(user.getUserId());
        log.debug("Saving MyRide via RideRequest: "+ myRideRequest);
        myRideService.save(myRideRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{rideId}")
    public ResponseEntity deleteMyRide(@PathVariable long rideId){
        myRideService.deleteMyRide(rideId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PostMapping("/update/")
    public ResponseEntity<Void> updateMyRide(@RequestBody MyRideRequest myRideRequest) {
        log.debug("Saving MyRide via RideRequest: "+ myRideRequest);
        MyRide myRide = myRideService.getMyRideById(myRideRequest.getId());
        myRide.setTimesRode(myRideRequest.getTimesRode());
        myRideService.save(myRide);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
