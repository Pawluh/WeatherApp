package paczwa.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import paczwa.controller.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewFactory {

    private List<Stage> activeStages;

    public ViewFactory() {
        activeStages = new ArrayList<>();
    }

    public void showMainWindow(){
        BaseController controller = new MainWindowController(this, "mainWindow.fxml");
        initializeStage(controller);
    }

    public void showTomorrowWeatherWindow(){
        BaseController controller = new TomorrowWeatherWindowController(this, "tomorrowWeatherWindow.fxml");
        initializeStage(controller);
    }

    public void showWeekForecastWindow(){
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
