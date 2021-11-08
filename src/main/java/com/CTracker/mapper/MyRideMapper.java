package com.CTracker.mapper;

import com.CTracker.dto.MyRideRequest;
import com.CTracker.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MyRideMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "ride", source = "ride")
    @Mapping(target = "park", source = "park")
    @Mapping(target = "timesRode", source = "myRideRequest.timesRode")
    @Mapping(target = "rankInPark", source = "myRideRequest.rankInPark")
    @Mapping(target = "rankOverall", source = "myRideRequest.rankOverall")
    @Mapping(target = "rating", source = "myRideRequest.rating")
    @Mapping(target = "review", source = "myRideRequest.review")
    @Mapping(target = "bestSeat", source = "myRideRequest.bestSeat")
    @Mapping(target = "firstRode", source = "myRideRequest.firstRode")
    @Mapping(target = "lastRode", source = "myRideRequest.lastRode")
    MyRide map(MyRideRequest myRideRequest, Park park, Ride ride, User user);
}
