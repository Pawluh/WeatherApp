package paczwa.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void findCity1Button() {

    }

    @FXML
    void findCity2Button() {

    }

}

