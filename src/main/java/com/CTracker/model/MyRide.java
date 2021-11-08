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
public class MyRide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @OneToOne
    @JoinColumn(name = "park_id", nullable = false)
    private Park park;

    @Column(name = "times_rode")
    private int timesRode;

    @Column(name = "rank_in_park")
    private int rankInPark;

    @Column(name = "rank_overall")
    private int rankOverall;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review")
    private String review;

    @Column(name = "best_seat")
    private String bestSeat;

    @Column(name = "first_rode")
    @CreationTimestamp
    private Date firstRode;

    @Column(name = "last_rode")
    private Date lastRode;

}
