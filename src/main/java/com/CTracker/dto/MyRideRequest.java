package com.CTracker.dto;

import com.CTracker.model.Park;
import com.CTracker.model.Ride;
import com.CTracker.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyRideRequest {
    private Long id;
    private Long userId;
    private Long rideId;
    private Long parkId;
    private int timesRode;
    private int rankInPark;
    private int rankOverall;
    private int rating;
    private String review;
    private String bestSeat;
    private Date firstRode;
    private Date lastRode;
}
