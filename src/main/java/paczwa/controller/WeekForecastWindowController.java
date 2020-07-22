package paczwa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

    private ObservableList<CityWeatherForecast> city1WeatherForecast;
    private ObservableList<CityWeatherForecast> city2WeatherForecast;

    public WeekForecastWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
        city1WeatherForecast = FXCollections.observableArrayList();
        city2WeatherForecast = FXCollections.observableArrayList();
    }

    @FXML
    void setWeatherForCity1Button() throws IOException {
        if(fieldWithCityNameIsValid(city1TextField.getText().isEmpty())) {
            // city1DateColumn.setCellValueFactory(cellData -> cellData.getValue().getPressureForSpecificDay());
            city1DescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
            city1MaxTempColumn.setCellValueFactory(cellData ->  cellData.getValue().getMaxTempForSpecificDay());
            city1MinTempColumn.setCellValueFactory(cellData ->  cellData.getValue().getMinTempForSpecificDay());

            try {
                getDataAboutWeather(city1WeatherForecast, city1TextField.getText());
                city1ForecastTableView.setItems(city1WeatherForecast);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void setWeatherForCity2Button() {
        if(fieldWithCityNameIsValid(city2TextField.getText().isEmpty())) {
            // city1DateColumn.setCellValueFactory(cellData -> cellData.getValue().getPressureForSpecificDay());
            city2DescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
            city2MaxTempColumn.setCellValueFactory(cellData ->  cellData.getValue().getMaxTempForSpecificDay());
            city2MinTempColumn.setCellValueFactory(cellData ->  cellData.getValue().getMinTempForSpecificDay());

            try {
                getDataAboutWeather(city2WeatherForecast, city2TextField.getText());
                city2ForecastTableView.setItems(city2WeatherForecast);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getDataAboutWeather(ObservableList<CityWeatherForecast> cityWeatherForecast, String cityName) throws IOException {
        for(int i =0 ; i<7 ; i++){
            CityWeatherForecast cityWeather = new CityWeatherForecast(cityName);
            cityWeather.getWeather(i);
           // cityWeather.setDescription(i);
            cityWeatherForecast.add(cityWeather);
        }
    }

    private boolean fieldWithCityNameIsValid(boolean cityName) {
        if(cityName) {
            System.out.println("Please fill email");
            return false;
        }
        return true;
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
