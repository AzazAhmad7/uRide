package com.project.uRide.entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double rating;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean available;

    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point currentLocation;
}
