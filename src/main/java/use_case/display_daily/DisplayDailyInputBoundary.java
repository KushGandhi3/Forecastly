package use_case.display_daily;

/**
 * Input Boundary for actions which are related to the displaying daily weather.
 */
public interface DisplayDailyInputBoundary {

    /**
     * Executes the display daily use case.
     * @param displayDailyInputData the input data
     */
    void execute(DisplayDailyInputData displayDailyInputData);

    /**
     * Executes the display daily use case without input data.
     */
    void execute();

    /**
     * Switches to home view.
     */
    void switchToHomeView();

}
