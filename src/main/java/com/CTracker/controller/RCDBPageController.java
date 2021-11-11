package com.CTracker.controller;

import com.CTracker.exceptions.PostNotFoundException;
import com.CTracker.model.RCDBPage;
import com.CTracker.repository.RCDBPageRepository;
import com.CTracker.service.RCDBPageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rcdb")
@AllArgsConstructor
@Slf4j
public class RCDBPageController {
    private final RCDBPageService rcdbPageService;
    private final RCDBPageRepository rcdbPageRepository;

    @PostMapping("/page/{pageId}")
    public ResponseEntity<Void> createPageEntry(@PathVariable Long pageId,@RequestBody String pageData) {
        log.debug("SAVING PAGE DATA FOR PAGE ID: " + pageId);
        RCDBPage rcdbPage = new RCDBPage(pageId,pageData,pageData.length(),rcdbPageService.extractTitle(pageData),
                rcdbPageService.extractRideName(rcdbPageService.extractTitle(pageData)),
                rcdbPageService.extractPark(rcdbPageService.extractTitle(pageData)),
                rcdbPageService.extractParkName(rcdbPageService.extractTitle(rcdbPageService.extractPark(rcdbPageService.extractTitle(pageData)))),
                rcdbPageService.extractPark(rcdbPageService.extractLocationString(rcdbPageService.extractPark(rcdbPageService.extractTitle(pageData)))),
                "","",""
        );
        rcdbPageService.save(rcdbPage);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/extractTitles")
    public ResponseEntity<Void> createPageEntry() {
        String extractedTitle;
        String extractedRide  = "";
        String extractedPark = "";
        String extractedParkName = "";
        String locationString = "";
        String city = "";
        String state = "";
        String country = "";
        for(long i = 0;i<15000;i++){
            RCDBPage rcdbPage = null;
            try{
                rcdbPage = rcdbPageRepository.findById(i).orElseThrow(() -> new PostNotFoundException("id not found"));
            }catch (Exception e){
                log.info("No page found for id: "+i);
            }
            log.debug("Pulled rcdbPage: "+rcdbPage);
            if (rcdbPage != null) {
                extractedTitle = rcdbPageService.extractTitle(rcdbPage.getData());
                if(extractedTitle.length()>2){
                    extractedRide = rcdbPageService.extractRideName(extractedTitle);
                    extractedPark = rcdbPageService.extractPark(extractedTitle);
                    extractedParkName = rcdbPageService.extractParkName(extractedPark);
                    locationString = rcdbPageService.extractLocationString(extractedPark);
                    city = rcdbPageService.extractCity(locationString);
                    state = rcdbPageService.extractState(locationString);
                    country = rcdbPageService.extractCountry(locationString);
                    rcdbPage.setPark(extractedPark);
                    rcdbPage.setRide(extractedRide);
                    rcdbPage.setParkName(extractedParkName);
                    rcdbPage.setLocationString(locationString);
                    rcdbPage.setCity(city);
                    rcdbPage.setState(state);
                    rcdbPage.setCountry(country);
                }
                log.info("Page data found id: "+ i +
                        " Title: " + extractedTitle +
                        " Ride: " + extractedRide +
                        " Park: " + extractedPark +
                        " Park Name: " + extractedParkName +
                        " Location String: " + locationString +
                        " City: " + city +
                        " State: " + state +
                        " Country: " + country
                );
                rcdbPage.setTitle(extractedTitle);
                rcdbPageService.save(rcdbPage);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
