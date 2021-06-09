package game_test.game.high_score;

import game_test.game.MainMenu;
import game_test.game.MenuButtonsArrangement;
import game_test.game.PopUpBox;
import game_test.game.SimpleButton;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HighScoreHandler {
    private AnchorPane layout;
    private String level, path;
    private VBox vbox;
    private boolean isAttached;
    private SimpleButton back;
    private SimpleButton reset;
    private boolean hasHighScore;
    private MenuButtonsArrangement menuButtonsArrangement;
    private ConfirmationPopUp confirmationPopUp;

    public HighScoreHandler(String level){
        layout = new AnchorPane();
        this.level = level;
        path = "src/game_test/game/high_score/high_score_file/" + level + ".txt";
        isAttached = false;
        confirmationPopUp = new ConfirmationPopUp();
        confirmationPopUp.setLayout(1000,1000);
        addResetButton();
    }
    public void setMenuButtonsArrangement(MenuButtonsArrangement menuButtonsArrangement){
        this.menuButtonsArrangement = menuButtonsArrangement;
    }
    public void setBackButton(SimpleButton back){
        this.back = back;
        this.back.setLayoutY(-80);
        this.back.setLayoutX(-60);
    }
    public void addResetButton(){
        this.reset = new SimpleButton("Reset",80, 40,18);
        layout.getChildren().add(this.reset);
        this.reset.setLayoutX(220);
        this.reset.setLayoutY(-80);
        this.reset.setOnAction(e-> resetClicked());
    }
    private void setResetDisable(){
        if (hasHighScore){
            reset.setDisable(false);
        }
        else {
            reset.setDisable(true);
        }
    }

    private void resetClicked()  {
        confirmationPopUp.showPopUp();
    }

    public void updateScore(){
        if(isAttached){
            //hide the previous vbox
            vbox.setLayoutX(1000);
            layout.getChildren().remove(vbox);
            layout.getChildren().remove(back);
        }
        isAttached = true;
        setupVbox();
        getScores();
        setResetDisable();
    }

    private void getScores() {
        try{
            Read_score score = new Read_score(path);
            User userName[] = score.userDetails();
            int people = score.getSize();

            if (people != 0){
                hasHighScore = true;
                for (int i = 0;i<people && i < 5;i++){
                    HBox hbox = new HBox();
                    Label name = new Label();
                    name.setText(userName[i].getName());
                    //name.setFill(Color.BROWN);
                    name.setMinWidth(120);
                    name.setMaxWidth(160);
                    name.setFont(Font.font(20));

                    Text time = new Text();
                    time.setText(userName[i].getTimeStr());
                    time.setFont(Font.font(20));
                    time.setFill(Color.GREEN);
                    hbox.getChildren().addAll(name, time);
                    hbox.setSpacing(100);
                    vbox.getChildren().add(hbox);
                }
            }
            else {
                hasHighScore = false;
                Text noHighScore = new Text("No High Score is set");
                noHighScore.setWrappingWidth(250);
                noHighScore.setFont(Font.font(20));
                noHighScore.setFill(Color.BLUE);
                noHighScore.setTextAlignment(TextAlignment.CENTER);
                vbox.getChildren().add(noHighScore);
            }
        }
        catch (IOException | ClassNotFoundException | FileIsEmpty e){
            System.out.println(e);
        }
    }

    private void setupVbox() {
        vbox = new VBox();
        Text levelTitle = new Text(level + " Difficulty");
        levelTitle.setWrappingWidth(250);
        levelTitle.setTextAlignment(TextAlignment.CENTER);
        levelTitle.setFont(Font.font(25));
        levelTitle.setFill(Color.GREEN);
        vbox.getChildren().add(levelTitle);
        vbox.setSpacing(40);
        layout.getChildren().add(vbox);
        layout.getChildren().add(back);
    }

    public AnchorPane getLayout(){
        return layout;
    }

    private class ConfirmationPopUp extends PopUpBox {
        private Label label;
        private VBox vbox;
        private HBox hbox;

        private int height = 40;
        private int width = 100;
        private SimpleButton yes, no;

        public  ConfirmationPopUp(){
            label = new Label("    Do you want to reset\n       the high score?  ");
            vbox = new VBox(40);

            addFont();
            addButtons();
            setupStuff();
        }
        private void setupStuff() {
            getLayout().getChildren().addAll(label,hbox);
            label.setLayoutX(50);
            label.setLayoutY(60);

            hbox.setLayoutX(90);
            hbox.setLayoutY(110);
        }

        private void addFont() {
            try{
                label.setFont(Font.loadFont(new FileInputStream("src/game_test/game/resources/fonts/ghostclan.ttf"),
                        18));

            }
            catch(Exception e){
                System.out.println("FONT NOT FOUND");
                label.setFont(Font.font("Tahoma", 18));
            }
        }



        private void addButtons() {
            yes = new SimpleButton("Yes", width, height, 25);
            yes.setOnAction(e-> {
                try {
                    yesClicked();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            no = new SimpleButton("No",width,height,25 );
            no.setOnAction(e->noClicked());
            hbox = new HBox(20);
            hbox.setLayoutX(90);
            hbox.getChildren().addAll(yes,no);

        }

        private void noClicked() {
            setLayout(1000,1000);
            menuButtonsArrangement.getPlay().setDisable(false);
            menuButtonsArrangement.getSettings().setDisable(false);
            menuButtonsArrangement.getHighscores().setDisable(false);
            menuButtonsArrangement.getExit().setDisable(false);
            menuButtonsArrangement.getHelp().setDisable(false);
            reset.setDisable(false);
            back.setDisable(false);
        }

        private void yesClicked() throws IOException {
            Files.delete(Path.of(path));
            noClicked();
            hasHighScore = false;
            updateScore();
        }

        public void showPopUp(){
            setLayout(280,260);

            menuButtonsArrangement.getPlay().setDisable(true);
            menuButtonsArrangement.getSettings().setDisable(true);
            menuButtonsArrangement.getHighscores().setDisable(true);
            menuButtonsArrangement.getExit().setDisable(true);
            menuButtonsArrangement.getHelp().setDisable(true);
            reset.setDisable(true);
            back.setDisable(true);

        }
        public void setLayout(int X, int Y){
            getSubScene().setLayoutY(Y);
            getSubScene().setLayoutX(X);
        }

    }

    public SubScene getConfirmationPopUpSubScene(){
        return confirmationPopUp.getSubScene();
    }

}
