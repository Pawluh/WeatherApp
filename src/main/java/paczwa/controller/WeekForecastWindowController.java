package paczwa.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import paczwa.model.CityWeatherForecast;
import paczwa.view.ViewFactory;

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
    private TableColumn<CityWeatherForecast, Integer> city1TempColumn;

    @FXML
    private TextField city1TextField;

    @FXML
    private TableView<CityWeatherForecast> city2ForecastTableView;

    @FXML
    private TableColumn<CityWeatherForecast, String> city2DateColumn;

    @FXML
    private TableColumn<CityWeatherForecast, String> city2DescriptionColumn;

    @FXML
    private TableColumn<CityWeatherForecast, Integer> city2TempColumn;

    @FXML
    private TextField city1TextField1;

    public WeekForecastWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void setWeatherForCity1Button() {

    }

    @FXML
    void setWeatherForCity2Button() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        city1DateColumn.setCellValueFactory(new PropertyValueFactory<CityWeatherForecast, String>("Dane"));
        city1DescriptionColumn.setCellValueFactory(new PropertyValueFactory<CityWeatherForecast, String>("cos"));
        city1TempColumn.setCellValueFactory(new PropertyValueFactory<CityWeatherForecast, Integer>("cos"));
    }
}
