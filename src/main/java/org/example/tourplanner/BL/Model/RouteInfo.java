package org.example.tourplanner.BL.Model;

public class RouteInfo {
    private final double distance;
    private final double duration;

    public RouteInfo(double distance, double duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public Double getDuration() {
        return duration;
    }
}
