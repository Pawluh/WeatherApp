package paczwa.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import paczwa.config.Messages;
import paczwa.model.CityWeatherForecast;
import paczwa.view.ViewFactory;

import java.io.IOException;
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
    void setWeatherForCity1Button() throws IOException {
        setCityData(city1TextField, city1WeatherForecast, errorLabel1, date1, actualTemp1, maxTemp1, minTemp1, humidity1, pressure1, clouds1, wind1);
    }

    @FXML
    void setWeatherForCity2Button() throws IOException {
        setCityData(city2TextField, city2WeatherForecast, errorLabel2, date2, actualTemp2, maxTemp2, minTemp2, humidity2, pressure2, clouds2, wind2);
    }

    private void setCityData(TextField cityTextField,
                             CityWeatherForecast cityWeatherForecast,
                             Label errorLabel, Label date,
                             Label actualTemp, Label maxTemp,
                             Label minTemp, Label humidity,
                             Label pressure, Label clouds,
                             Label wind) throws IOException {
        if (fieldWithCityNameIsValid(cityTextField.getText().isEmpty())) {
            cityWeatherForecast.setCityName(cityTextField.getText());
            cityWeatherForecast.getWeather(DAYS_FROM_TODAY);

            if (cityWeatherForecast.getJsonDataCorrect()) {
                errorLabel.setText("");
                date.setText(cityWeatherForecast.getDate().get());
                actualTemp.setText(cityWeatherForecast.getCurrentFeelsLikeTemp().toString() + "\u2103");
                maxTemp.setText(cityWeatherForecast.getMaxTempForSpecificDay().get());
                minTemp.setText(cityWeatherForecast.getMinTempForSpecificDay().get());
                humidity.setText(cityWeatherForecast.getHumidityForSpecificDay() + "%");
                pressure.setText(cityWeatherForecast.getPressureForSpecificDay() + "mbar");
                clouds.setText(cityWeatherForecast.getCloudsForSpecificDay() + "%");
                wind.setText(cityWeatherForecast.getWindForSpecificDay() + "km/h");
            } else {
                errorLabel.setText(Messages.WRONG_CITY);
            }
        } else {
            errorLabel.setText(Messages.EMPTY_CITY_NAME);
        }
    }

    private boolean fieldWithCityNameIsValid(boolean cityName) {
        if (cityName) {
            return false;
        }
        return true;
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

    }
}

