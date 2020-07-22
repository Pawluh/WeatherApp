package paczwa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import paczwa.model.CityWeatherForecast;
import paczwa.view.ViewFactory;

import java.io.IOException;

public class MainWindowController extends BaseController {

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
    private Label actualTemp1;

    @FXML
    private Label maxTemp1;

    @FXML
    private Label minTemp1;

    @FXML
    private Label actualTemp2;

    @FXML
    private Label maxTemp2;

    @FXML
    private Label minTemp2;

    private CityWeatherForecast city1WeatherForecast;
    private CityWeatherForecast city2WeatherForecast;
    private Integer daysFromToday = 0;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {

        super(viewFactory, fxmlName);
        city1WeatherForecast = new CityWeatherForecast("");
        city2WeatherForecast = new CityWeatherForecast("");
    }

    //zmienic nazwe przycisków
    @FXML
    void setWeatherForCity1Button() throws IOException {
        if(fieldWithCityNameIsValid(city1TextField.getText().isEmpty())){
            city1WeatherForecast.setCityName(city1TextField.getText());
            city1WeatherForecast.getWeather(daysFromToday);
            actualTemp1.setText(city1WeatherForecast.getCurrentFeelsLikeTemp().toString() + "\u2103");
            maxTemp1.setText(city1WeatherForecast.getMaxTempForSpecificDay().toString() + "\u2103");
            minTemp1.setText(city1WeatherForecast.getMinTempForSpecificDay().toString() + "\u2103");
            humidity1.setText(city1WeatherForecast.getHumidityForSpecificDay() +"%");
            pressure1.setText(city1WeatherForecast.getPressureForSpecificDay() + "mbar");
            clouds1.setText(city1WeatherForecast.getCloudsForSpecificDay() + "%");
            wind1.setText(city1WeatherForecast.getWindForSpecificDay() +"km/h");
        }
        else{
            //  errorLabel.setText("Please fill email"); // dodac labela o error
        }
    }
    //zmienic nazwe przycisków
    @FXML
    void setWeatherForCity2Button() throws IOException {
        if(fieldWithCityNameIsValid(city2TextField.getText().isEmpty())){
            city2WeatherForecast.setCityName(city2TextField.getText());
            city2WeatherForecast.getWeather(daysFromToday);
            actualTemp2.setText(city2WeatherForecast.getCurrentFeelsLikeTemp().toString() + "\u2103");
            maxTemp2.setText(city2WeatherForecast.getMaxTempForSpecificDay().toString() + "\u2103");
            minTemp2.setText(city2WeatherForecast.getMinTempForSpecificDay().toString() + "\u2103");
            humidity2.setText(city2WeatherForecast.getHumidityForSpecificDay() +"%");
            pressure2.setText(city2WeatherForecast.getPressureForSpecificDay() + "mbar");
            clouds2.setText(city2WeatherForecast.getCloudsForSpecificDay() + "%");
            wind2.setText(city2WeatherForecast.getWindForSpecificDay() +"km/h");
        }
        else{
            //  errorLabel.setText("Please fill email"); // dodac labela o error
        }
    }

    private boolean fieldWithCityNameIsValid(boolean cityName) {
        if(cityName) {
            System.out.println("Please fill email");
            return false;
        }
        return true;
    }

}

