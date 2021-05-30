package game_test.game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainDisplay {
    //Main display dimensions
    private static int WIDTH = 960;
    private static int HEIGHT = 720;

    private static AnchorPane mainDisplayLayout;
    private static Scene mainDisplayScene;
    private static Stage mainDisplayStage;
    private GameDisplay gameDisplay;


    static private MainMenu mainMenu;

    public MainDisplay(){
        mainDisplayLayout = new AnchorPane();
        mainDisplayScene = new Scene(mainDisplayLayout, WIDTH, HEIGHT);
        mainDisplayStage = new Stage();
        mainDisplayStage.setScene(mainDisplayScene);
       // mainDisplayStage.initStyle(StageStyle.TRANSPARENT);
        mainDisplayStage.setResizable(false);
        mainDisplayScene.setFill(Color.TRANSPARENT);
        //css diya kono karone thik moto kaam kore na
        //mainDisplayScene.getStylesheets().add("game_test/game/game_test.game.resources/CSS/transparent_background.css");

        addBackGround();
        addMainMenu();
        addSubScenes();
    }

    private static void addSubScenes(){
        addGameDisplay();
        addSettingsDisplay();
        addHelpDisplay();
        addHighscoresDisplay();
    }

    private static void addHighscoresDisplay() {
        mainDisplayLayout.getChildren().add(mainMenu.getHighscoresDisplaySubScene());
    }

    private static void addSettingsDisplay() {
        mainDisplayLayout.getChildren().add(mainMenu.getSettingsDisplaySubScene());
    }

    private static void addHelpDisplay() {
        mainDisplayLayout.getChildren().add(mainMenu.getHelpDisplaySubScene());
    }

    private static void addBackGround() {
        Image backgroundImage = new Image("game_test/game/resources/menu_images/backgroundEmpty.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,null);
        mainDisplayLayout.setBackground(new Background(background));
    }

    public static Stage getMainDisplayStage(){
        return mainDisplayStage;
    }
    private static void addMainMenu(){
        mainMenu = new MainMenu();
        mainMenu.setMainDisplayStage(mainDisplayStage);
        mainDisplayLayout.getChildren().add(mainMenu.getMainMenuLayout());

    }
    private static void addGameDisplay(){
        mainDisplayLayout.getChildren().add(mainMenu.getGameDisplaySubScene());
    }
}
