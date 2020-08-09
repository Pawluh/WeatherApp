package paczwa;

import javafx.application.Application;
import javafx.stage.Stage;
import paczwa.view.ViewFactory;



public class App extends Application {

    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainWindow();
    }

    public static void main(String[] args) {
        launch();
    }

}