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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class RCDBPageService {
    private final RCDBPageRepository rcdbPageRepository;

    public RCDBPage save(RCDBPage rcdbPage) {
        return rcdbPageRepository.save(rcdbPage);
    }

    public String extractTitle(String pageData) {
        String extractedString = "";
       // Pattern p = Pattern.compile("(?<=(<title>))(\\w|\\d|\\n|[().,\\-:;@#$%^&*\\[\\]\"'+–/\\/®°⁰!?{}|`~]| )+?(?=(</title>))");

        Pattern p = Pattern.compile("(?<=(<title>))(.*?)(?=(</title>))");

        Matcher m = p.matcher(pageData);
        if(m.find()){
            extractedString = m.group();
        }
        return extractedString;
    }

    public String extractRideName(String title){
        String extractedString = "";
        Pattern p = Pattern.compile(".*(?= - )");
        Matcher m = p.matcher(title);
        if(m.find()){
            extractedString = m.group();
        }
        return extractedString;
    }

    public String extractPark(String title){
        String extractedString = "";
        Pattern p = Pattern.compile("(?<= - ).*");
        Matcher m = p.matcher(title);
        if(m.find()){
            extractedString = m.group();
        }
        return extractedString;
    }
    public String extractParkName(String park){
        String extractedString = "";
        Pattern p = Pattern.compile("^.*?(?=\\s\\()");
        Matcher m = p.matcher(park);
        if(m.find()){
            extractedString = m.group();
        }
        return extractedString;
    }
    public String extractLocationString(String park){
        String extractedString = "";
        Pattern p = Pattern.compile("(?<value>(?<=\\().*(?=\\)))");
        Matcher m = p.matcher(park);
        if(m.find()){
            extractedString = m.group();
        }
        return extractedString;
    }
    public String extractCountry(String locationString){
        String extractedString = "";
        Pattern p = Pattern.compile("[^,]*$");
        Matcher m = p.matcher(locationString);
        if(m.find()){
            extractedString = m.group();
        }
        return extractedString.trim();
    }
    public String extractState(String locationString){
        String extractedString = "";
        Pattern p = Pattern.compile("(?<=,[\\s])[^,]+(?=,)");
        Matcher m = p.matcher(locationString);
        if(m.find()){
            extractedString = m.group();
        }
        return extractedString.trim();
    }
    public String extractCity(String locationString){
        String extractedString = "";
        Pattern p = Pattern.compile("^([^,]*)");
        Matcher m = p.matcher(locationString);
        if(m.find()){
            extractedString = m.group();
        }
        return extractedString.trim();
    }

}
