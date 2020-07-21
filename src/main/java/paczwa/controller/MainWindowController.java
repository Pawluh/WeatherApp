package paczwa.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import paczwa.model.CityWeatherForecast;
import paczwa.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController {

    @FXML
    private Label humidity1;

    @FXML
    private Label pressure1;

    @FXML
    private Label rain1;

    @FXML
    private Label wind1;

    @FXML
    private TextField city1TextField;

    @FXML
    private Label humidity2;

    @FXML
    private Label pressure2;

    @FXML
    private Label rain2;

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

    private CityWeatherForecast cityWeatherForecast;
    public MainWindowController(ViewFactory viewFactory, String fxmlName) {

        super(viewFactory, fxmlName);
        cityWeatherForecast = new CityWeatherForecast("");
    }

    @FXML
    void findCity1Button() {
        System.out.println("Find first city button!!");
        if(fieldWithCityNameIsValid(city1TextField.getText().isEmpty())){
            cityWeatherForecast.setCityName(city1TextField.getText());
            cityWeatherForecast.setCurrentWeather();
            maxTemp1.setText(String.valueOf(cityWeatherForecast.getCurrentMaxTemp()));
        }
        else{
            //  errorLabel.setText("Please fill email"); // dodac labela o error
        }

    }

    @FXML
    void findCity2Button() {
        System.out.println("Find second city button!!");
        
        // praktycznie to samo co dla pierwszego buttona
    }

    private boolean fieldWithCityNameIsValid(boolean cityName) {
        if(cityName) {

            System.out.println("Please fill email");
            return false;
        }
        return true;
    }

}

