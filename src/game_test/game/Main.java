package game_test.game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainDisplay mainDisplay = new MainDisplay();
        primaryStage = mainDisplay.getMainDisplayStage();
        primaryStage.show();
    }
}
