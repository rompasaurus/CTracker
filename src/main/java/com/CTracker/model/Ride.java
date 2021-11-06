package com.CTracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "times_rode")
    private int timesRode;

    @Column(name = "description")
    private String description;

    @Column(name = "first_rode")
    @CreationTimestamp
    private Date firstRode;

    @Column(name = "last_rode")
    @CreationTimestamp
    private Instant lastRode;

    @OneToOne
    @JoinColumn(name = "park_id", nullable = false)
    private Park park;


}
