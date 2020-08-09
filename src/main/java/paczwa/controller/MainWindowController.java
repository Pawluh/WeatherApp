package paczwa.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import paczwa.config.Messages;
import paczwa.model.CityNameValidator;
import paczwa.model.CityWeatherForecast;
import paczwa.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

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

    @FXML
    private Label date1;

    @FXML
    private Label date2;

    @FXML
    private Label errorLabel1;

    @FXML
    private Label errorLabel2;

    private final CityWeatherForecast city1WeatherForecast;
    private final CityWeatherForecast city2WeatherForecast;
    private static final int DAYS_FROM_TODAY = 0;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
        city1WeatherForecast = new CityWeatherForecast("");
        city2WeatherForecast = new CityWeatherForecast("");
    }

    @FXML
    void setWeatherForCity1Button() {
        setCityData(city1TextField, city1WeatherForecast,
                errorLabel1, date1,
                actualTemp1, maxTemp1,
                minTemp1, humidity1,
                pressure1, clouds1, wind1);
    }

    @FXML
    void setWeatherForCity2Button() {
        setCityData(city2TextField, city2WeatherForecast,
                errorLabel2, date2,
                actualTemp2, maxTemp2,
                minTemp2, humidity2,
                pressure2, clouds2, wind2);
    }

    private void setCityData(TextField cityTextField,
                             CityWeatherForecast cityWeatherForecast,
                             Label errorLabel, Label date,
                             Label actualTemp, Label maxTemp,
                             Label minTemp, Label humidity,
                             Label pressure, Label clouds,
                             Label wind) {
        if (CityNameValidator.validate(cityTextField.getText())) {
            cityWeatherForecast.setCityName(cityTextField.getText());

            if (cityWeatherForecast.setWeather(DAYS_FROM_TODAY)) {
                errorLabel.setText("");
                date.setText(cityWeatherForecast.getWeather().getDate());
                actualTemp.setText(cityWeatherForecast.getWeather().getFeelsLikeTemp().toString() + "\u2103");
                maxTemp.setText(cityWeatherForecast.getWeather().getMaxTemp());
                minTemp.setText(cityWeatherForecast.getWeather().getMinTemp());
                humidity.setText(cityWeatherForecast.getWeather().getHumidity() + "%");
                pressure.setText(cityWeatherForecast.getWeather().getPressure() + "mbar");
                clouds.setText(cityWeatherForecast.getWeather().getClouds() + "%");
                wind.setText(cityWeatherForecast.getWeather().getWind() + "km/h");
            } else {
                errorLabel.setText(Messages.WRONG_CITY);
            }
        } else {
            errorLabel.setText(Messages.EMPTY_CITY_NAME);
        }
    }

    @FXML
    void showTomorrowWeatherButton() {
        viewFactory.showTomorrowWeatherWindow();
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
        //empty
    }
}

