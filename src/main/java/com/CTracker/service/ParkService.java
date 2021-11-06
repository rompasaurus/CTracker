package com.CTracker.service;

import com.CTracker.dto.ParkRequest;
import com.CTracker.dto.SubredditDto;
import com.CTracker.mapper.ParkMapper;
import com.CTracker.model.Park;
import com.CTracker.model.Subreddit;
import com.CTracker.repository.ParkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class ParkService {
    private final ParkRepository parkRepository;
    private final ParkMapper parkMapper;

    @Transactional(readOnly = true)
    public List<Park> getAllParks() {
        return parkRepository.findAll()
                .stream()
                .collect(toList());
    }
    @Transactional
    public Park save(ParkRequest parkRequest) {
        Park savedPark = parkRepository.save(parkMapper.map(parkRequest));
        return savedPark;
    }
}