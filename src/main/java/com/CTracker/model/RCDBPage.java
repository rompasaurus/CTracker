package com.CTracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RCDBPage {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "data")
    private String data;

    @Column(name = "size")
    private int size;

    @Column(name = "title")
    private String title;

    @Column(name = "ride")
    private String ride;

    @Column(name = "park")
    private String park;

    @Column(name = "parkName")
    private String parkName;

    @Column(name = "locationString")
    private String locationString;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;
}
