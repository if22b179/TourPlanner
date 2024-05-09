package org.example.tourplanner.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class Tour {

    private String name;
    private String description;
    private String from;
    private String to;
    private String transportType;
    private double distance;
    private String estimatedTime;
}
