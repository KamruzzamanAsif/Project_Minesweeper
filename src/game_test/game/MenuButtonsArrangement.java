package game_test.game;

import javafx.scene.layout.VBox;


public class MenuButtonsArrangement {
    private static int buttonDistance = 50;
    private int nextButtonStart = 0;
    private VBox vbox;

    private MenuButton play;
    private MenuButton settings;
    private MenuButton help;
    private MenuButton exit;
    private MenuButton highscores;

    private int cnt = 0;
    public MenuButtonsArrangement(){
        play = new MenuButton("Play");
        settings = new MenuButton("Settings");
        help = new MenuButton("Help");
        exit = new MenuButton("Exit");
        highscores = new MenuButton("HighScores");

        vbox = new VBox();
        vbox.setStyle("-fx-background-color: transparent;");
        addPlayButton();
        addSettingsButton();
        addHelpButton();
        addHighscoresButton();
        addExitButton();
        vbox.setSpacing(buttonDistance);
    }

    public VBox getMenuButtonsArrangementLayout(){
        return vbox;
    }

    public void setMenuButtonsArrangementLayout(int X, int Y){
        vbox.setLayoutX(X);
        vbox.setLayoutY(Y);
    }
    private void addPlayButton() {
        vbox.getChildren().add(play);
        play.setMouseButtonReleasedStyle();
    }
    private void addSettingsButton(){
        vbox.getChildren().add(settings);
        settings.setLayoutY(nextButtonStart);
    }
    private void addHelpButton(){
        vbox.getChildren().add(help);
        help.setLayoutY(nextButtonStart);
    }
    private void addHighscoresButton() {
        vbox.getChildren().add(highscores);
        highscores.setDefaultStyle();
    }
    private void addExitButton(){
        vbox.getChildren().add(exit);
        System.out.println(nextButtonStart);
    }
    public MenuButton getPlay() {
        return play;
    }
    public MenuButton getSettings() {
        return settings;
    }
    public MenuButton getHelp() {
        return help;
    }
    public MenuButton getHighscores(){
        return highscores;
    }
    public MenuButton getExit() {
        return exit;
    }
}
