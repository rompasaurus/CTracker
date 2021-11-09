package com.CTracker.controller;

import com.CTracker.dto.ParkRequest;
import com.CTracker.model.Park;
import com.CTracker.model.RCDBPage;
import com.CTracker.model.Ride;
import com.CTracker.service.ParkService;
import com.CTracker.service.RCDBPageService;
import com.CTracker.service.RideService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Clob;
import java.util.List;

@RestController
@RequestMapping("/api/rcdb")
@AllArgsConstructor
@Slf4j
public class RCDBPageController {
    private final RCDBPageService rcdbPageService;

    @PostMapping("/page/{pageId}")
    public ResponseEntity<Void> createPageEntry(@PathVariable Long pageId,@RequestBody String pageData) {
        log.debug("SAVING PAGE DATA FOR PAGE ID: " + pageId);
        RCDBPage rcdbPage = new RCDBPage(pageId,pageData,pageData.length());
        rcdbPageService.save(rcdbPage);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
