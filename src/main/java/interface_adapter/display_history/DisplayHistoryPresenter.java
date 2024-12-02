package interface_adapter.display_history;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_daily.DisplayDailyViewModel;
import interface_adapter.display_home.DisplayHomeViewModel;
import interface_adapter.display_summarization.DisplaySummarizationViewModel;
import use_case.display_history.DisplayHistoryOutputBoundary;
import use_case.display_history.DisplayHistoryOutputData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DisplayHistoryPresenter manages the preparation and presentation of the
 * historical view of cities in the application.
 */
public class DisplayHistoryPresenter implements DisplayHistoryOutputBoundary {

    private final DisplayHistoryViewModel displayHistoryViewModel;
    private final DisplayHomeViewModel displayHomeViewModel;
    private final DisplayDailyViewModel displayDailyViewModel;
    private final DisplaySummarizationViewModel displaySummarizationViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayHistoryPresenter(DisplayHistoryViewModel displayHistoryViewModel,
                                   DisplayHomeViewModel displayHomeViewModel,
                                   DisplayDailyViewModel displayDailyViewModel,
                                   DisplaySummarizationViewModel displaySummarizationViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.displayHistoryViewModel = displayHistoryViewModel;
        this.displayHomeViewModel = displayHomeViewModel;
        this.displayDailyViewModel = displayDailyViewModel;
        this.displaySummarizationViewModel = displaySummarizationViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepare view to display history.
     */
    @Override
    public void prepareSuccessView(DisplayHistoryOutputData cities) {
        final DisplayHistoryState displayHistoryState = displayHistoryViewModel.getState();
        displayHistoryState.setCities(cities.getCities());

        displayHistoryViewModel.setState(displayHistoryState);
        displayHistoryViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        final DisplayHistoryState displayHistoryState = displayHistoryViewModel.getState();
        final List<String> cities = new ArrayList<>();
        cities.add(errorMessage);
        displayHistoryState.setCities(cities);

        displayHistoryViewModel.setState(displayHistoryState);
        displayHistoryViewModel.firePropertyChanged();
    }

    /**
     * Prepare view to switch to home view.
     */
    @Override
    public void switchToHomeView() {
        displayHomeViewModel.firePropertyChanged("update_data");

        viewManagerModel.setState(displayHomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
