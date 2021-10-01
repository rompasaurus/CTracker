package com.CTracker.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ride")
@Data
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
    private Date lastRode;

    @OneToOne
    @JoinColumn(name = "park_id", nullable = false)
    private Park park;


}
