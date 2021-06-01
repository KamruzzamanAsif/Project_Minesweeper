package game_test.game;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
    HBox easy,medium,hard; // for game difficulty
    HBox on, off; // for music play
    private static final int hboxSpacing = 150;
    SettingsSubsceneButtons easyDifficultyButton, mediumDifficultyButton, hardDifficultyButton;
    SettingsSubsceneButtons onButton, offButton; // music on off button
    SettingsLabel easyLabel, mediumLabel, hardLabel, head1;
    SettingsLabel onLabel, offLabel, head2; // music on off label
    SettingsSubsceneButtons save;
    int temp_difficulty;
    int difficulty;
    boolean musicPlay;

    GameDisplay gameDisplay;
    Music music;

    public SettingsSubScene(){
        layout = new AnchorPane();

        easyDifficultyButton = new SettingsSubsceneButtons();
        mediumDifficultyButton = new SettingsSubsceneButtons();
        hardDifficultyButton = new SettingsSubsceneButtons();

        onButton = new SettingsSubsceneButtons();
        offButton = new SettingsSubsceneButtons();

        // game mode labels
        easyLabel = new SettingsLabel("Easy");
        mediumLabel = new SettingsLabel("Medium");
        hardLabel = new SettingsLabel("Hard");
        head1 = new SettingsLabel("   Game Mode",30);

        // music labels
        onLabel = new SettingsLabel("ON");
        offLabel = new SettingsLabel("OFF");
        head2 = new SettingsLabel("   Game Music", 30);

        vbox = new VBox();
        vbox.setSpacing(15);

        easy = new HBox();
        easy.setSpacing(hboxSpacing);

        medium = new HBox();
        medium.setSpacing(hboxSpacing);

        hard = new HBox();
        hard.setSpacing(hboxSpacing);

        on = new HBox();
        on.setSpacing(hboxSpacing);

        off = new HBox();
        off.setSpacing(hboxSpacing);

        //save button for changing the difficulty
        save = new SettingsSubsceneButtons("/game_test/game/resources/settings_subscene_images/grey_button15.png",
                "game_test/game/resources/settings_subscene_images/grey_button14.png", 100, 49);
        save.setMouseButtonPressedStyle();

        addLabels();
        addButtons();
        addHBox();
        addVBox();
        easyDifficultyButton.setMouseButtonPressedStyle();
        onButton.setMouseButtonPressedStyle();
        setListeners();
        temp_difficulty = 0;
        difficulty = 0;
    }

    public void setGameDisplay(GameDisplay gameDisplay){
        this.gameDisplay = gameDisplay;
    }
    private void addVBox() {
        layout.getChildren().add(vbox);
        vbox.setLayoutY(15);
        vbox.setLayoutX(-15);
    }

    private class SettingsLabel extends Label{
        public SettingsLabel(String text){
            super(text);
            setMinHeight(easyDifficultyButton.getPrefHeight());
            setMinWidth(100);
            setTextAlignment(TextAlignment.CENTER);
            setLabelFont();
        }
        public SettingsLabel(String text, int size){
            super(text);
            setMinHeight(easyDifficultyButton.getPrefHeight());
            setMinWidth(100);
            setTextAlignment(TextAlignment.CENTER);
            setLabelFont(size);
            setTextFill(Color.INDIGO);

        }
        private void setLabelFont(){
            try{
                setFont(Font.loadFont(new FileInputStream("src/game_test/game/resources/fonts/ghostclan.ttf"),
                        20.0));

            }
            catch(Exception e){
                System.out.println("FONT NOT FOUND");
                setFont(Font.font("Tahoma", 20.0));
            }

        }
        private void setLabelFont(int size){
            try{
                setFont(Font.loadFont(new FileInputStream("src/game_test/game/resources/fonts/ghostclan.ttf"),
                        size));

            }
            catch(Exception e){
                System.out.println("FONT NOT FOUND");
                setFont(Font.font("Tahoma", 20.0));
            }

        }

    }

    private void addLabels() {
        head1.setLayoutX(-5);
        head1.setLayoutY(-5);
        head1.setAlignment(Pos.CENTER);
        easy.getChildren().add(easyLabel);
        medium.getChildren().add(mediumLabel);
        hard.getChildren().add(hardLabel);

        head2.setLayoutX(-5);
        head2.setLayoutY(-5);
        head2.setAlignment(Pos.CENTER);
        on.getChildren().add(onLabel);
        off.getChildren().add(offLabel);

        save.setText("save");
    }

    private void addButtons() {
        easy.getChildren().add(easyDifficultyButton);
        medium.getChildren().add(mediumDifficultyButton);
        hard.getChildren().add(hardDifficultyButton);

        on.getChildren().add(onButton);
        off.getChildren().add(offButton);

        layout.getChildren().add(save);

        AnchorPane.setTopAnchor(save, 300.0);
        save.setLayoutX(75);
    }

    private void addHBox(){
        vbox.getChildren().addAll(head1,easy,medium,hard,head2,on,off);
    }

    public AnchorPane getLayout(){
        return layout;
    }

    private void setListeners() {
        easyDifficultyButton.setOnAction(e->easyDifficultyButtonPressed());
        mediumDifficultyButton.setOnAction(e->mediumDifficultyButtonPressed());
        hardDifficultyButton.setOnAction(e->hardDifficultyButtonPressed());

        onButton.setOnAction(e->onButtonPressed());
        offButton.setOnAction(e->offButtonPressed());

        save.setOnAction(e->saveButtonPressed());
    }

    private void saveButtonPressed() {
        if (save.getIsPressed()) return;

        difficulty = temp_difficulty;

        save.setIsPressed(true);
        save.setMouseButtonPressedStyle();

        gameDisplay.newDifficulty(difficulty);

        music = new Music();
        music.MusicPlay(musicPlay);
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

    private void onButtonPressed(){
        musicPlay = true;
        offButton.setDefaultStyle();

        if (!save.isPressed()){
            save.setDefaultStyle();
        }
    }

    private void offButtonPressed(){
        musicPlay = false;
        onButton.setDefaultStyle();

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
