package com.CTracker.service;

import com.CTracker.dto.ParkRequest;
import com.CTracker.dto.SubredditDto;
import com.CTracker.mapper.ParkMapper;
import com.CTracker.model.Park;
import com.CTracker.model.Ride;
import com.CTracker.model.Subreddit;
import com.CTracker.repository.ParkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.annotations.Cacheable;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class ParkService {
    private final ParkRepository parkRepository;
    private final ParkMapper parkMapper;

    @Cacheable("parks")
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

    public List<String> getAllCountries() {
        return parkRepository.findAllCountries()
                .stream()
                .collect(toList());
    }
    public List<String> getAllCitiesByState(String state) {
        return parkRepository.findAllCitiesByState(state)
                .stream()
                .collect(toList());
    }
    public List<String> getAllStatesByCountry(String country) {
        return parkRepository.findAllStatesByCountry(country)
                .stream()
                .collect(toList());
    }
    public List<Park> getAllParksByCity(String city) {
        return parkRepository.findAllParksByCity(city)
                .stream()
                .collect(toList());
    }
    public List<Park> getAllParksByState(String state) {
        return parkRepository.findAllParksByState(state)
                .stream()
                .collect(toList());
    }
    public List<Park> getAllParksByCountry(String country) {
        return parkRepository.findAllParksByCountry(country)
                .stream()
                .collect(toList());
    }

    public List<String> getAllCities() {
            return parkRepository.findAllCities()
                    .stream()
                    .collect(toList());
    }
}
