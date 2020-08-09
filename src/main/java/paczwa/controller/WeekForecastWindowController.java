package paczwa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import paczwa.config.Messages;
import paczwa.model.CityNameValidator;
import paczwa.model.CityWeatherForecast;
import paczwa.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WeekForecastWindowController extends BaseController implements Initializable {
    @FXML
    private TableView<CityWeatherForecast> city1ForecastTableView;

    @FXML
    private TableColumn<CityWeatherForecast, String> city1DateColumn;

    @FXML
    private TableColumn<CityWeatherForecast, String> city1DescriptionColumn;

    @FXML
    private TableColumn<CityWeatherForecast, String> city1MaxTempColumn;

    @FXML
    private TableColumn<CityWeatherForecast, String> city1MinTempColumn;

    @FXML
    private TextField city1TextField;

    @FXML
    private TableView<CityWeatherForecast> city2ForecastTableView;

    @FXML
    private TableColumn<CityWeatherForecast, String> city2DateColumn;

    @FXML
    private TableColumn<CityWeatherForecast, String> city2DescriptionColumn;

    @FXML
    private TableColumn<CityWeatherForecast, String> city2MaxTempColumn;

    @FXML
    private TableColumn<CityWeatherForecast, String> city2MinTempColumn;

    @FXML
    private TextField city2TextField;

    @FXML
    private Label errorLabel1;

    @FXML
    private Label errorLabel2;

    private final ObservableList<CityWeatherForecast> city1WeatherForecast;
    private final ObservableList<CityWeatherForecast> city2WeatherForecast;

    public WeekForecastWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
        city1WeatherForecast = FXCollections.observableArrayList();
        city2WeatherForecast = FXCollections.observableArrayList();
    }

    @FXML
    void setWeatherForCity1Button() {
        setCityData(city1TextField, errorLabel1, city1WeatherForecast, city1DateColumn, city1DescriptionColumn, city1MaxTempColumn, city1MinTempColumn, city1ForecastTableView);
    }

    @FXML
    void setWeatherForCity2Button() {
        setCityData(city2TextField, errorLabel2, city2WeatherForecast, city2DateColumn, city2DescriptionColumn, city2MaxTempColumn, city2MinTempColumn, city2ForecastTableView);
    }

    private void setCityData(TextField cityTextField,
                             Label errorLabel,
                             ObservableList<CityWeatherForecast> cityWeatherForecast,
                             TableColumn<CityWeatherForecast, String> cityDateColumn,
                             TableColumn<CityWeatherForecast, String> cityDescriptionColumn,
                             TableColumn<CityWeatherForecast, String> cityMaxTempColumn,
                             TableColumn<CityWeatherForecast, String> cityMinTempColumn,
                             TableView<CityWeatherForecast> cityForecastTableView) {
        if (CityNameValidator.validate(cityTextField.getText())) {
            errorLabel.setText("");
            cityWeatherForecast.clear();
            cityDateColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());
            cityDescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
            cityMaxTempColumn.setCellValueFactory(cellData -> cellData.getValue().getMaxTempForSpecificDay());
            cityMinTempColumn.setCellValueFactory(cellData -> cellData.getValue().getMinTempForSpecificDay());

            try {
                getDataAboutWeather(cityWeatherForecast, cityTextField.getText());
                cityForecastTableView.setItems(cityWeatherForecast);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            errorLabel.setText(Messages.EMPTY_CITY_NAME);
        }
    }

    private void getDataAboutWeather(ObservableList<CityWeatherForecast> cityWeatherForecast, String cityName) throws IOException {
        for(int i =0 ; i<7 ; i++){
            CityWeatherForecast cityWeather = new CityWeatherForecast(cityName);
            cityWeather.getWeather(i);
            if(cityWeather.getJsonDataCorrect()){
                cityWeatherForecast.add(cityWeather);
            }
            else{
                errorLabel1.setText("Zle wpsiane miasto. (nie używaj polskich liter)");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    void showTodayWeatherButton() {
        viewFactory.showMainWindow();
        Stage stage = (Stage) city1TextField.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void showTomorrowWeatherButton() {
        viewFactory.showTomorrowWeatherWindow();
        Stage stage = (Stage) city1TextField.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
