package paczwa.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import paczwa.controller.*;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {

    private ArrayList<Stage> activeStages;

    public ViewFactory() {
        activeStages = new ArrayList<Stage>();
    }

    public void showMainWindow(){
        System.out.println("main window called");
        BaseController controller = new MainWindowController(this, "mainWindow.fxml");
        initializeStage(controller);
    }

    public void showTomorrowWeatherWindow(){
        System.out.println("tomorrow weather window called");
        BaseController controller = new TomorrowWeatherWindowController(this, "tomorrowWeatherWindow.fxml");
        initializeStage(controller);
    }

    public void showWeekForecastWindow(){
        System.out.println("Forecast for 10 days");
        BaseController controller = new WeekForecastWindowController(this, "weekForecastWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/paczwa/" + baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public  void closeStage(Stage stageToClose){
        stageToClose.close();
        activeStages.remove(stageToClose);
    }
}
