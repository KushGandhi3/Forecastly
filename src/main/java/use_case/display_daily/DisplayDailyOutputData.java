package use_case.display_daily;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Output Data for the Display Daily use-case.
 */
public class DisplayDailyOutputData {

    private final String city;
    // list of the weekdays in the correct order
    private final List<String> weekdays;
    // list of the temperatures in order of the weekdays
    private final List<String> temperatures;
    private final List<String> conditions;
    // selected weather details for the particular weekday
    private final String feelsLikeTemperature;
    private final String uvIndex;
    private final String windSpeed;
    private final String cloudCover;
    private final String precipitation;
    private final String humidity;

    public DisplayDailyOutputData(JSONObject outputDataPackage) {
        this.city = outputDataPackage.getString("city");

        JSONArray weekdaysArray = outputDataPackage.getJSONArray("weekdays");
        this.weekdays = parseJSONArray(weekdaysArray, String.class);

        JSONArray temperaturesArray = outputDataPackage.getJSONArray("temperatures");
        this.temperatures = parseJSONArray(temperaturesArray, String.class);

        JSONArray conditionsArray = outputDataPackage.getJSONArray("conditions");
        this.conditions = parseJSONArray(conditionsArray, String.class);

        this.feelsLikeTemperature = outputDataPackage.getString("feelsLikeTemperature");
        this.uvIndex = outputDataPackage.getString("uvIndex");
        this.windSpeed = outputDataPackage.getString("windSpeed");
        this.cloudCover = outputDataPackage.getString("cloudCover");
        this.precipitation = outputDataPackage.getString("precipitation");
        this.humidity = outputDataPackage.getString("humidity");
    }

    /**
     * Parses a JSONArray that contains elements of generic type. Returns a list containing the parsed data in order.
     * @param jsonArray the JSONArray to parse
     * @param type the type of the data being parsed
     * @param <T> the type class of the data being parsed
     * @throws IllegalArgumentException when data to be parsed is not of type <T>
     */
    private <T> List<T> parseJSONArray(JSONArray jsonArray, Class<T> type) throws IllegalArgumentException {
        List<T> dataValues = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (type.isInstance(value)) {
                dataValues.add(type.cast(value));
            } else {
                throw new IllegalArgumentException("Element at index " + i + " is not of type " + type.getSimpleName());
            }
        }
        return dataValues;
    }

    public String getCity() {
        return city;
    }

    public List<String> getWeekdays() {
        return weekdays;
    }

    public List<String> getTemperatures() {
        return temperatures;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public String getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getHumidity() {
        return humidity;
    }

}