package data_access.recent_city;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import entity.recent_city.RecentCityData;
import entity.recent_city.RecentCityDataFactory;
import exception.RecentCitiesDataException;
import use_case.display_daily.DisplayDailyRecentCitiesDAI;
import use_case.display_history.DisplayHistoryDAI;
import use_case.display_home.DisplayHomeRecentCitiesDAI;
import use_case.display_hourly.DisplayHourlyRecentCitiesDAI;
import use_case.display_summarization.DisplaySummarizationRecentCitiesDAI;

/**
 * DAO for accessing data about recently viewed cities.
 */
public class RecentCitiesDAO implements DisplayDailyRecentCitiesDAI, DisplayHomeRecentCitiesDAI,
        DisplaySummarizationRecentCitiesDAI, DisplayHistoryDAI, DisplayHourlyRecentCitiesDAI {

    private static final Path RECENT_CITIES_PATH =
            Path.of("src", "main", "resources", "data", "RecentCities.json");
    private final RecentCityDataFactory recentCityDataFactory;

    public RecentCitiesDAO(RecentCityDataFactory recentCityDataFactory) {
        this.recentCityDataFactory = recentCityDataFactory;
    }

    /**
     * Add a city to the recently viewed cities list. The data will be added to the recent city data file.
     * @param city the city to add to the recently viewed city list
     * @throws RecentCitiesDataException when there is an issue writing data
     */
    @Override
    public void addCity(String city) throws RecentCitiesDataException {
        final JSONArray recentCitiesArray = readRecentCities();
        final List<String> recentCitiesList = new ArrayList<>();

        // Add the city to the front and remove duplicates
        recentCitiesList.add(city);
        for (int i = 0; i < recentCitiesArray.length(); i++) {
            final String existingCity = recentCitiesArray.getString(i);
            if (!existingCity.equals(city)) {
                recentCitiesList.add(existingCity);
            }
        }

        // Update the JSON Array and write to the file
        writeToRecentCities(new JSONArray(recentCitiesList));
    }

    /**
     * Reads the recent cities from the JSON file and returns the corresponding RecentCityData entity.
     * @return a list of recent city names
     */
    @Override
    public RecentCityData getRecentCityData() throws RecentCitiesDataException {
        final JSONArray recentCitiesArray = readRecentCities();

        // create an array list of the city names
        final List<String> cityList = new ArrayList<>(recentCitiesArray.length());
        for (int i = 0; i < recentCitiesArray.length(); i++) {
            cityList.add(recentCitiesArray.getString(i));
        }

        return this.recentCityDataFactory.create(cityList);
    }

    /**
     * Read the RecentCities json file from resource. Return a JSONArray of the data in the file.
     * @return JSONArray containing the recent cities
     * @throws RecentCitiesDataException when the RecentCities json file is not found
     */
    private JSONArray readRecentCities() throws RecentCitiesDataException {
        try {
            final String jsonString = Files.readString(RECENT_CITIES_PATH);
            return new JSONArray(jsonString);
        }
        catch (IOException exception) {
            throw new RecentCitiesDataException("Failed To Get Recent City Data. Failed To Read File: "
                    + RECENT_CITIES_PATH + "."
            );
        }
        catch (org.json.JSONException exception) {
            throw new RecentCitiesDataException(
                    "Failed To Get Recent City Data. Failed To Parse File: " + RECENT_CITIES_PATH + "."
            );
        }
    }

    /**
     * Write the recent cities array to the RecentCities JSON file.
     * @param recentCitiesArray the array of cities to write to the JSON file
     * @throws RecentCitiesDataException when the RecentCities file cannot be found or written to
     */
    private void writeToRecentCities(JSONArray recentCitiesArray) throws RecentCitiesDataException {
        try {
            Files.writeString(RECENT_CITIES_PATH, recentCitiesArray.toString());
        }
        catch (IOException exception) {
            throw new RecentCitiesDataException("Failed To Get Recent City Data. Failed To Write To File: "
                    + RECENT_CITIES_PATH);
        }
    }
}
