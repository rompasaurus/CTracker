package com.CTracker.mapper;

import com.CTracker.dto.ParkRequest;
import com.CTracker.dto.RideRequest;
import com.CTracker.model.Park;
import com.CTracker.model.Ride;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RideMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "rideRequest.name")
    @Mapping(target = "timesRode", constant = "1")
    @Mapping(target = "description", source = "rideRequest.description")
    @Mapping(target = "firstRode", source = "rideRequest.firstRode")
    @Mapping(target = "lastRode", source = "rideRequest.lastRode")
    @Mapping(target = "park", source = "park")
    Ride map(RideRequest rideRequest, Park park);

//    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
//    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
//    CommentsDto mapToDto(Comment comment);
}