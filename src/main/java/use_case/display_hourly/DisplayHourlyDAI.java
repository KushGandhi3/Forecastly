package use_case.display_hourly;

import entity.weather.WeatherData;
import exception.APICallException;

/**
 * The interface of the DAO for the weather data used by all use cases.
 */
public interface DisplayHourlyDAI {

    /**
     * Get Weather data from the API.
     * @param location the name of the location.
     * @return the weather data.
     * @throws APICallException if the request fails.
     */
    WeatherData getWeatherData(String location) throws APICallException;
}