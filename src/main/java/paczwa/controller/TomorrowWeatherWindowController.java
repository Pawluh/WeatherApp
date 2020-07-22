package paczwa.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import paczwa.model.CityWeatherForecast;
import paczwa.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TomorrowWeatherWindowController extends BaseController implements Initializable {

    @FXML
    private Label humidity1;

    @FXML
    private Label pressure1;

    @FXML
    private Label clouds1;

    @FXML
    private Label wind1;

    @FXML
    private TextField city1TextField;

    @FXML
    private Label humidity2;

    @FXML
    private Label pressure2;

    @FXML
    private Label clouds2;

    @FXML
    private Label wind2;

    @FXML
    private TextField city2TextField;

    @FXML
    private Label maxTemp1;

    @FXML
    private Label minTemp1;

    @FXML
    private Label maxTemp2;

    @FXML
    private Label minTemp2;

    @FXML
    private Label date1;

    @FXML
    private Label date2;

    @FXML
    private Label errorLabel1;

    @FXML
    private Label errorLabel2;

    private CityWeatherForecast city1WeatherForecast;
    private CityWeatherForecast city2WeatherForecast;
    private int daysFromToday = 1;

    public TomorrowWeatherWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
        city1WeatherForecast = new CityWeatherForecast("");
        city2WeatherForecast = new CityWeatherForecast("");
    }


    @FXML
    void setWeatherForCity1Button() throws IOException {
        if(fieldWithCityNameIsValid(city1TextField.getText().isEmpty())){
            city1WeatherForecast.setCityName(city1TextField.getText());
            city1WeatherForecast.getWeather(daysFromToday);

            if(city1WeatherForecast.getJsonDataCorrect()) {
                errorLabel1.setText("");
                date1.setText(city1WeatherForecast.getDate().get());
                maxTemp1.setText(city1WeatherForecast.getMaxTempForSpecificDay().get());
                minTemp1.setText(city1WeatherForecast.getMinTempForSpecificDay().get());
                humidity1.setText(city1WeatherForecast.getHumidityForSpecificDay() + "%");
                pressure1.setText(city1WeatherForecast.getPressureForSpecificDay() + "mbar");
                clouds1.setText(city1WeatherForecast.getCloudsForSpecificDay() + "%");
                wind1.setText(city1WeatherForecast.getWindForSpecificDay() + "km/h");
            }else {
                errorLabel1.setText("Zle wpsiane miasto. (nie używaj polskich liter)");
            }
        }
        else{
            errorLabel1.setText("Podaj nazwe miasta");
        }
    }

    @FXML
    void setWeatherForCity2Button() throws IOException {
        if(fieldWithCityNameIsValid(city2TextField.getText().isEmpty())) {
            city2WeatherForecast.setCityName(city2TextField.getText());
            city2WeatherForecast.getWeather(daysFromToday);

            if(city2WeatherForecast.getJsonDataCorrect()) {
                errorLabel2.setText("");
                date2.setText(city2WeatherForecast.getDate().get());
                maxTemp2.setText(city2WeatherForecast.getMaxTempForSpecificDay().get());
                minTemp2.setText(city2WeatherForecast.getMinTempForSpecificDay().get());
                humidity2.setText(city2WeatherForecast.getHumidityForSpecificDay() + "%");
                pressure2.setText(city2WeatherForecast.getPressureForSpecificDay() + "mbar");
                clouds2.setText(city2WeatherForecast.getCloudsForSpecificDay() + "%");
                wind2.setText(city2WeatherForecast.getWindForSpecificDay() + "km/h");
            }else {
                errorLabel2.setText("Zle wpsiane miasto. (nie używaj polskich liter)");
            }
        }
        else{
            errorLabel2.setText("Podaj nazwe miasta");
        }
    }

    private boolean fieldWithCityNameIsValid(boolean cityName) {
        if(cityName) {
            return false;
        }
        return true;
    }

    @FXML
    void showTodayWeatherButton() {
        viewFactory.showMainWindow();
        Stage stage = (Stage) maxTemp1.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void showWeekForecastButton() {
        viewFactory.showWeekForecastWindow();
        Stage stage = (Stage) maxTemp1.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}

