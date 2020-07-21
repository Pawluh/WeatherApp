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

    /*
    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

     */


    @Override
    public void start(Stage stage) throws Exception {
/*
        String fxmlFile = "paczwa/mainWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 700, 580);

        stage.setTitle("Weather App");
        stage.setScene(scene);
        stage.show();
*/

        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainWindow();
    }




    /*
    @Override
    public void start(Stage primaryStage) throws IOException {

            AnchorPane root = (AnchorPane) FXMLLoader.load(App.class.getResource("mainWindow.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Bluerift Timeline");
            primaryStage.show();

    }

     */

/*
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML("mainWindow"));
        stage.setScene(scene);
        stage.show();

    }
*/


    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
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