package com.CTracker.service;

import com.CTracker.dto.RideRequest;
import com.CTracker.exceptions.ParkNotFoundException;
import com.CTracker.exceptions.RideNotFoundException;
import com.CTracker.mapper.RideMapper;
import com.CTracker.model.Park;
import com.CTracker.model.RCDBPage;
import com.CTracker.model.Ride;
import com.CTracker.repository.ParkRepository;
import com.CTracker.repository.RCDBPageRepository;
import com.CTracker.repository.RideRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class RCDBPageService {
    private final RCDBPageRepository rcdbPageRepository;

    public RCDBPage save(RCDBPage rcdbPage) {
        return rcdbPageRepository.save(rcdbPage);
    }

}
