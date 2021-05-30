package game_test.game;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;


public class HelpSubScene {
    AnchorPane layout;
    StackPane stackPane;
    Text text;
    GameDisplay gameDisplay;

    public HelpSubScene(){
        text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text.setWrappingWidth(200);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setText("The quick brown fox jumps over the lazy dog");

        stackPane = new StackPane(text);

        layout.getChildren().add(stackPane);
    }

    public AnchorPane getLayout(){
        return layout;
    }
    public void setGameDisplay(GameDisplay gameDisplay){
        this.gameDisplay = gameDisplay;
    }
}
