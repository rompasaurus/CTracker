package com.CTracker.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="park")
//@Data
@Getter
@Setter
public class Park {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "park_name")
    private String parkName;

    @Column(name = "location")
    private String location;

    @Column(name = "open_date")
    @CreationTimestamp
    private Date openDate;
}
