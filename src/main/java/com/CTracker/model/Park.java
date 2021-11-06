package com.CTracker.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;
import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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
    private Instant openDate;
}
