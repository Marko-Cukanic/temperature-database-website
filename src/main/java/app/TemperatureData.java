package app;

import java.util.ArrayList;
import java.util.List;

public class TemperatureData {
    private int year;
    private float averageTemperature;
    private float minimumTemperature;
    private float maximumTemperature;
    private String country;
    private String region;

    public TemperatureData(int year, float averageTemperature, float minimumTemperature, float maximumTemperature, String country, String region) {
        this.year = year;
        this.averageTemperature = averageTemperature;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.country = country;
        this.region = region;
    }

    public int getYear() {
        return year;
    }

    public float getAverageTemperature() {
        return averageTemperature;
    }

    public float getMinimumTemperature() {
        return minimumTemperature;
    }

    public float getMaximumTemperature() {
        return maximumTemperature;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }
   
}


