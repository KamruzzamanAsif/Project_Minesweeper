package game_test.game;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class HighScoresSubScene {
    private HBox easy,medium,hard;
    private VBox vbox;
    private AnchorPane layout;
    private HighScoresSubSceneLabel easyLabel, mediumLabel, hardLabel;
    private HighScoresSubSceneLabel easyTime, mediumTime, hardTime;

    private static final int hboxspacing = 50;
    public HighScoresSubScene(){
        layout = new AnchorPane();
        easy = new HBox();
        easy.setSpacing(hboxspacing);

        medium = new HBox();
        medium.setSpacing(hboxspacing);

        hard = new HBox();
        hard.setSpacing(hboxspacing);

        vbox = new VBox();
        vbox.setSpacing(40);

        addEasy();
        addMedium();
        addHard();
        addvbox();
    }

    private void addvbox() {
        vbox.getChildren().addAll(easy,medium,hard);
        layout.getChildren().add(vbox);
        vbox.setLayoutX(-25);
    }

    public AnchorPane getLayout() {
        return layout;
    }

    private void addEasy() {
        easyLabel = new HighScoresSubSceneLabel("easy");
        easyLabel.setMinWidth(180);
        easyTime = new HighScoresSubSceneLabel();
        updateEasy();
        easy.getChildren().addAll(easyLabel,easyTime);

    }
    private void addMedium(){
        mediumLabel = new HighScoresSubSceneLabel("medium");
        mediumLabel.setMinWidth(180);
        mediumTime = new HighScoresSubSceneLabel();
        updateMedium();
        medium.getChildren().addAll(mediumLabel,mediumTime);
    }

    private void addHard(){
        hardLabel = new HighScoresSubSceneLabel("hard");
        hardLabel.setMinWidth(180);
        hardTime = new HighScoresSubSceneLabel();
        updateHard();
        hard.getChildren().addAll(hardLabel,hardTime);
    }

    public void updateTimes(){
        updateEasy();
        updateMedium();
        updateHard();
    }

    private void updateHard() {
        FileReader reader;
        BufferedReader bufferedReader;
        String x;
        try{
            reader = new FileReader("src/game_test/game/highscores/2.dat");
            bufferedReader = new BufferedReader(reader);
            String s = bufferedReader.readLine();
            if (s != null) x = s;
            else x = "-1";
            reader.close();
            bufferedReader.close();
        }
        catch (IOException e){
            x = "-1";
        }
        hardTime.setText(getTimeString(x));
    }

    private void updateMedium() {
        FileReader reader;
        BufferedReader bufferedReader;
        String x;
        try{
            reader = new FileReader("src/game_test/game/highscores/1.dat");
            bufferedReader = new BufferedReader(reader);
            String s = bufferedReader.readLine();
            if (s != null) x = s;
            else x = "-1";
            reader.close();
            bufferedReader.close();
        }
        catch (IOException e){
            x = "-1";
        }
        mediumTime.setText(getTimeString(x));
    }

    private void updateEasy() {
        FileReader reader;
        BufferedReader bufferedReader;
        String x;
        try{
            reader = new FileReader("src/game_test/game/highscores/0.dat");
            bufferedReader = new BufferedReader(reader);
            String s = bufferedReader.readLine();
            if (s != null) x = s;
            else x = "-1";
            reader.close();
            bufferedReader.close();
        }
        catch (IOException e){
            x = "-1";
        }
        easyTime.setText(getTimeString(x));
    }

    private String getTimeString(String x){
        if (x.equals("-1")) return "Not set";

        int time,minutes,seconds;
        time = Integer.parseInt(x);
        minutes = time / 60;
        seconds = time % 60;

        return((((minutes/10) == 0) ? "0" : "") + minutes + ":"
                + (((seconds/10) == 0) ? "0" : "") + seconds);
    }
    private class HighScoresSubSceneLabel extends Label{
        public HighScoresSubSceneLabel(String text){
            super (text);
            setLabelFont();
        }
        public HighScoresSubSceneLabel(){
            setLabelFont();
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
    }
}
