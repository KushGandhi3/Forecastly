package entity;

public class CommonWeather implements Weather {

    Double currentTemperature;
    String location;

    public CommonWeather(Double currentTemperature, String location) {
        this.currentTemperature = currentTemperature;
        this.location = location;
    }

    @Override
    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    @Override
    public String getLocation() {
        return location;
    }

}
