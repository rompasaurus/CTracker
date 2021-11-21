package com.CTracker.service;

import com.CTracker.dto.MyRideRequest;
import com.CTracker.dto.SubredditDto;
import com.CTracker.exceptions.RideNotFoundException;
import com.CTracker.exceptions.SpringRedditException;
import com.CTracker.mapper.MyRideMapper;
import com.CTracker.model.*;
import com.CTracker.repository.MyRideRepository;
import com.CTracker.repository.ParkRepository;
import com.CTracker.repository.RideRepository;
import com.CTracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class MyRideService {

    private final MyRideRepository myRideRepository;
    private final UserRepository userRepository;
    private final MyRideMapper myRideMapper;
    private final AuthService authService;
    private final ParkRepository parkRepository;
    private final RideRepository rideRepository;

    @Transactional
    public MyRideRequest save(MyRideRequest myRideRequest) {
        User user = authService.getCurrentUser();
        Park park = parkRepository.getById(myRideRequest.getParkId());
        Ride ride = rideRepository.getById(myRideRequest.getRideId());
        MyRide save = myRideRepository.save(myRideMapper.map(myRideRequest,park,ride,user));
        myRideRequest.setId(save.getId());
        return myRideRequest;
    }
    @Transactional
    public MyRide save(MyRide myRide) {
        MyRide save = myRideRepository.save(myRide);
        return save;
    }

    @Transactional(readOnly = true)
    public List<MyRide> getAll(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return myRideRepository.findAllByUser(user)
                .stream()
                .collect(toList());
    }
    public void deleteMyRide(Long id) {
        myRideRepository.deleteById(id);
    }

    public MyRide getMyRideById(Long id){
        return  myRideRepository.getById(id);
    }

}
