package game_test.game;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;

public class SettingsSubScene {
    AnchorPane layout;
    VBox vbox;
    HBox easy,medium,hard;
    private static final int hboxSpacing = 50;
    SettingsSubsceneButtons easyDifficultyButton, mediumDifficultyButton, hardDifficultyButton;
    SettingsLabel easyLabel, mediumLabel, hardLabel, head;
    SettingsSubsceneButtons save;
    int temp_difficulty;
    int difficulty;

    GameDisplay gameDisplay;

    public SettingsSubScene(){
        layout = new AnchorPane();
        easyDifficultyButton = new SettingsSubsceneButtons();
        mediumDifficultyButton = new SettingsSubsceneButtons();
        hardDifficultyButton = new SettingsSubsceneButtons();

        easyLabel = new SettingsLabel("Easy");
        mediumLabel = new SettingsLabel("Medium");
        hardLabel = new SettingsLabel("Hard");
        head = new SettingsLabel("Change Difficulty",30);

        vbox = new VBox();
        vbox.setSpacing(20);

        easy = new HBox();
        easy.setSpacing(hboxSpacing);

        medium = new HBox();
        medium.setSpacing(hboxSpacing);

        hard = new HBox();
        hard.setSpacing(hboxSpacing);

        //save button for changing the difficulty

        save = new SettingsSubsceneButtons("/game_test/game/resources/settings_subscene_images/grey_button15.png",
                "game_test/game/resources/settings_subscene_images/grey_button14.png", 100, 49);
        save.setMouseButtonPressedStyle();

        addLabels();
        addButtons();
        addHBox();
        addVBox();
        easyDifficultyButton.setMouseButtonPressedStyle();
        setListeners();
        temp_difficulty = 0;
        difficulty = 0;
    }

    public void setGameDisplay(GameDisplay gameDisplay){
        this.gameDisplay = gameDisplay;
    }
    private void addVBox() {
        layout.getChildren().add(vbox);
        vbox.setLayoutY(100);
        vbox.setLayoutX(-15);
    }

    private class SettingsLabel extends Label{
        public SettingsLabel(String text){
            super(text);
            setMinHeight(easyDifficultyButton.getPrefHeight());
            setMinWidth(200);
            setTextAlignment(TextAlignment.CENTER);
            setLabelFont();
        }
        public SettingsLabel(String text, int size){
            super(text);
            setMinHeight(easyDifficultyButton.getPrefHeight());
            setMinWidth(200);
            setTextAlignment(TextAlignment.CENTER);
            setLabelFont(size);
            setTextFill(Color.INDIGO);

        }
        private void setLabelFont(){
            try{
                setFont(Font.loadFont(new FileInputStream("src/game_test/game/resources/fonts/ghostclan.ttf"),
                        25.0));

            }
            catch(Exception e){
                System.out.println("FONT NOT FOUND");
                setFont(Font.font("Tahoma", 25.0));
            }

        }
        private void setLabelFont(int size){
            try{
                setFont(Font.loadFont(new FileInputStream("src/game_test/game/resources/fonts/ghostclan.ttf"),
                        size));

            }
            catch(Exception e){
                System.out.println("FONT NOT FOUND");
                setFont(Font.font("Tahoma", 25.0));
            }

        }

    }

    private void addLabels() {
        layout.getChildren().add(head);
        head.setLayoutX(-25);
        easy.getChildren().add(easyLabel);
        medium.getChildren().add(mediumLabel);
        hard.getChildren().add(hardLabel);

        save.setText("save");
    }

    private void addButtons() {
        easy.getChildren().add(easyDifficultyButton);
        medium.getChildren().add(mediumDifficultyButton);
        hard.getChildren().add(hardDifficultyButton);
        layout.getChildren().add(save);

        AnchorPane.setTopAnchor(save, 300.0);
        save.setLayoutX(75);
    }

    private void addHBox(){
        vbox.getChildren().addAll(easy,medium,hard);
    }

    public AnchorPane getLayout(){
        return layout;
    }

    private void setListeners() {
        easyDifficultyButton.setOnAction(e->easyDifficultyButtonPressed());
        mediumDifficultyButton.setOnAction(e->mediumDifficultyButtonPressed());
        hardDifficultyButton.setOnAction(e->hardDifficultyButtonPressed());
        save.setOnAction(e->saveButtonPressed());
    }

    private void saveButtonPressed() {
        if (save.getIsPressed()) return;

        difficulty = temp_difficulty;
        save.setIsPressed(true);
        save.setMouseButtonPressedStyle();

        gameDisplay.newDifficulty(difficulty);

    }

    private void hardDifficultyButtonPressed() {
        temp_difficulty = 2;
        easyDifficultyButton.setDefaultStyle();
        mediumDifficultyButton.setDefaultStyle();

        if (!save.isPressed()){
            save.setDefaultStyle();
        }
    }

    private void mediumDifficultyButtonPressed() {
        temp_difficulty = 1;
        easyDifficultyButton.setDefaultStyle();
        hardDifficultyButton.setDefaultStyle();

        if (!save.isPressed()){
            save.setDefaultStyle();
        }
    }

    private void easyDifficultyButtonPressed() {
        temp_difficulty = 0;
        mediumDifficultyButton.setDefaultStyle();
        hardDifficultyButton.setDefaultStyle();

        if (!save.isPressed()){
            save.setDefaultStyle();
        }
    }
    public void setHiddenStyle(){
        if (difficulty == 0){
            easyDifficultyButton.setMouseButtonPressedStyle();
            mediumDifficultyButton.setDefaultStyle();
            hardDifficultyButton.setDefaultStyle();
        }
        else if (difficulty == 1){
            easyDifficultyButton.setDefaultStyle();
            mediumDifficultyButton.setMouseButtonPressedStyle();
            hardDifficultyButton.setDefaultStyle();
        }
        else{
            easyDifficultyButton.setDefaultStyle();
            mediumDifficultyButton.setDefaultStyle();
            hardDifficultyButton.setMouseButtonPressedStyle();
        }

        save.setMouseButtonPressedStyle();
    }

}
