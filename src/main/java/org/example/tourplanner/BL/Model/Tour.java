package org.example.tourplanner.BL.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tour")
@Data
@NoArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "fromDest", nullable = false)
    private String from;

    @Column(name = "toDest", nullable = false)
    private String to;

    @Column(name = "transportType", nullable = false)
    private String transportType;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @Column(name = "estimatedTime", nullable = false)
    private String estimatedTime;

    @Column(name = "image", nullable = false)
    private String image;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TourLog> tourLogs = new ArrayList<>();

    public Tour(String name, String description, String from, String to, String transportType, Double distance, String estimatedTime, String image) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.image = image;
    }
}
