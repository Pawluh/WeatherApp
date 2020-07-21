package paczwa.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import paczwa.controller.*;

import java.io.IOException;

public class ViewFactory {

    public void showMainWindow(){
        System.out.println("main window called");

        BaseController controller = new MainWindowController(this, "mainWindow.fxml");
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
    }
}
