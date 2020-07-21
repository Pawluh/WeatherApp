package paczwa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import paczwa.config.OWMConfig;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println( "Hello World!" );

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



        launch();
    }

}