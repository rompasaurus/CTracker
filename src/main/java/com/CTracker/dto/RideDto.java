package com.CTracker.dto;

import com.CTracker.model.Park;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RideDto {
    private Long id;
    private String name;
    private int timesRode;
    private String description;
    private Date firstRode;
    private Date lastRode;
    private String parkId;
}


