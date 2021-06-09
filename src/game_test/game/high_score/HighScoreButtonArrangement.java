package game_test.game.high_score;

import game_test.game.MenuButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HighScoreButtonArrangement {
    private static int buttonDistance = 50;
    private int nextButtonStart = 0;
    private VBox vbox;
    private HBox hbox;

    private MenuButton easy;
    private MenuButton medium;
    private MenuButton hard;

    public HighScoreButtonArrangement(){
        easy = new MenuButton("Easy");
        medium = new MenuButton("Medium");
        hard = new MenuButton("Hard");

        vbox = new VBox();
        vbox.setStyle("-fx-background-color: transparent;");

        addEasyButton();
        addMediumButton();
        addHardButton();
        setDefault();
        vbox.setSpacing(buttonDistance);

    }

    public VBox getHighScoreButtonsArrangementLayout(){

        return vbox;
    }

    public void setHighScoreButtonsArrangementLayout(int X, int Y){
        vbox.setLayoutX(X);
        vbox.setLayoutY(Y);
    }

    private void addEasyButton(){
        vbox.getChildren().add(easy);
        easy.setMouseButtonReleasedStyle();     //in start mouse button is pressed
    }

    private void addMediumButton(){
        vbox.getChildren().add(medium);
        medium.setMouseButtonReleasedStyle();
    }

    private void addHardButton(){
        vbox.getChildren().add(hard);
        hard.setMouseButtonReleasedStyle();
    }

    public void setDefault(){
        easy.setDefaultStyle();
        medium.setDefaultStyle();
        hard.setDefaultStyle();
    }

    public MenuButton getEasy() {
        return easy;
    }
    public MenuButton getMedium() {
        return medium;
    }
    public MenuButton getHard() {
        return hard;
    }

}
