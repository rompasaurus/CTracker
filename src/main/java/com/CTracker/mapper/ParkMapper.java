package com.CTracker.mapper;

import com.CTracker.dto.ParkRequest;
import com.CTracker.model.Park;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParkMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parkName", source = "parkRequest.parkName")
    @Mapping(target = "location", source = "parkRequest.location")
    @Mapping(target = "openDate", expression = "java(java.time.Instant.now())")
    Park map(ParkRequest parkRequest);

//    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
//    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
//    CommentsDto mapToDto(Comment comment);
}