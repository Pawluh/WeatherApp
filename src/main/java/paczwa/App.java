package paczwa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import paczwa.config.OWMConfig;
import paczwa.controller.MainWindowController;
import paczwa.view.ViewFactory;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainWindow();
    }



    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        System.out.println( "Hello World!" );
        /*
        OWMConfig config = new OWMConfig();
        OWM openWeatherMap = new OWM(config.getApiKey());
        openWeatherMap.setUnit(OWM.Unit.METRIC);
        System.out.println(config.getApiKey());

        try{
            CurrentWeather currentWeather = openWeatherMap.currentWeatherByCityName("Wroclaw");
            System.out.println("Miasto: " + currentWeather.getCityName());

            System.out.println("Temperatura: " + currentWeather.getMainData().getTempMax());
        }catch (APIException e){
            System.out.println(e.getMessage());
        }
        */
        launch();
    }

}