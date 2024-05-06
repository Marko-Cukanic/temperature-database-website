package app;

public class tempdatacountry {
    private int year;
    private String country;
    private float averageTemperature;
    private float minimumTemperature;
    private float maximumTemperature;

    public tempdatacountry(int year, float averageTemperature, float minimumTemperature, float maximumTemperature, String country) {
        this.year = year;
        this.averageTemperature = averageTemperature;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
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
}