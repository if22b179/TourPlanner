package org.example.tourplanner.BL.Model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tourLogs")
@Data
@NoArgsConstructor
public class TourLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourLogs_id")
    private Integer id;

    @Column(name = "dateTime", nullable = false)
    private String dateTime;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "difficulty", nullable = false)
    private Integer difficulty;

    @Column(name = "totalDistance", nullable = false)
    private Double totalDistance;

    @Column(name = "totalTime", nullable = false)
    private Double totalTime;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    public TourLog(String dateTime, String comment, Integer difficulty, Double totalDistance, Double totalTime, Integer rating) {
        this.dateTime = dateTime;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.rating = rating;
    }
}
